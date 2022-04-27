package ink.kangaroo.common.core.utils.sql;

import com.alibaba.fastjson.JSON;
import ink.kangaroo.common.core.annotation.Excel;
import ink.kangaroo.common.core.utils.ExcelUtil;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.core.utils.bean.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {


    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";

    /**
     * 根据文件后缀获取对应Workbook对象 * * @param filePath * @param fileType * @return
     */
    public static Workbook getWorkbook(String filePath, String fileType) {

        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        try {

            File excelFile = new File(filePath);
            if (!excelFile.exists()) {

                logger.info(filePath + "文件不存在");
                return null;
            }
            fileInputStream = new FileInputStream(excelFile);
            if (fileType.equalsIgnoreCase(XLS)) {

                workbook = new HSSFWorkbook(fileInputStream);
            } else if (fileType.equalsIgnoreCase(XLSX)) {

                workbook = new XSSFWorkbook(fileInputStream);
            }
        } catch (Exception e) {

            logger.error("获取文件失败", e);
        } finally {

            try {

                if (null != fileInputStream) {

                    fileInputStream.close();
                }
            } catch (Exception e) {

                logger.error("关闭数据流出错！错误信息：", e);
                return null;
            }
        }
        return workbook;
    }

    public static List<Object> readFolder(String filePath) {

        int fileNum = 0;
        File file = new File(filePath);
        List<Object> returnList = new ArrayList<>();
        List<Map<String, String>> resultList = new ArrayList<>();
        if (file.exists()) {

            File[] files = file.listFiles();
            for (File file2 : files) {

                if (file2.isFile()) {

                    resultList = readExcel(file2.getAbsolutePath());
                    returnList.add(resultList);
                    fileNum++;
                }
            }
        } else {

            logger.info("文件夹不存在");
            return null;
        }
        logger.info("共有文件：" + fileNum);
        return returnList;
    }

    /**
     * 读取Excel中文件的特定的表，返回数据对象 * * @param filePath 文件路径 * @param sheetName 表格名字（索引） * @return
     */
    public static List<Map> readExcel(String filePath, String sheetName) {

        Workbook workbook = null;
        List<Map> resultList = new ArrayList<>();
        try {

            String fileType = filePath.substring(filePath.lastIndexOf("."));
            workbook = getWorkbook(filePath, fileType);
            if (workbook == null) {

                logger.info("获取workbook对象失败");
                return null;
            }
            resultList = analysisExcel(workbook, sheetName);
            return resultList;
        } catch (Exception e) {

            logger.error("读取Excel文件失败" + filePath + "错误信息", e);
            return null;
        } finally {

            try {

                if (null != workbook) {

                    workbook.close();
                }
            } catch (Exception e) {

                logger.error("关闭数据流出错！错误信息：", e);
                return null;
            }

        }
    }

    /**
     * 批量读取Excel中的文件所有的表，返回数据对象 * * @param filePath 文件路径 * @return
     */
    public static List<Map<String, String>> readExcel(String filePath) {

        Workbook workbook = null;
        List<Map<String, String>> resultList = new ArrayList<>();
        try {

            String fileType = filePath.substring(filePath.lastIndexOf("."));
            workbook = getWorkbook(filePath, fileType);
            if (workbook == null) {

                logger.info("获取workbook对象失败");
                return null;
            }
            resultList = analysisExcel(workbook);
            return resultList;
        } catch (Exception e) {

            logger.error("读取Excel文件失败" + filePath + "错误信息", e);
            return null;
        } finally {

            try {

                if (null != workbook) {

                    workbook.close();
                }
            } catch (Exception e) {

                logger.error("关闭数据流出错！错误信息：", e);
                return null;
            }

        }
    }

    /**
     * 解析Excel文件中特定的表，返回数据对象 * * @param workbook 生成对应的excel处理 * @param sheetName 表格名字（索引） * @return
     */
    public static List<Map> analysisExcel(Workbook workbook, String sheetName) {

        List<Map> dataList = new ArrayList<>();

        int sheetCount = workbook.getNumberOfSheets();//或取一个Excel中sheet数量
        for (int i = 0; i < sheetCount; i++) {

            if (!sheetName.equals(workbook.getSheetName(i))) {

                continue;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            int firstRowCount = sheet.getFirstRowNum();//获取第一行的序号
            Row firstRow = sheet.getRow(firstRowCount);
            int cellCount = firstRow.getLastCellNum();//获取列数

            List<String> mapKey = new ArrayList<>();

            //获取表头信息，放在List中备用
            if (firstRow == null) {

                logger.info("解析Excel失败，在第一行没有读取到任何数据！");
            } else {

                for (int i1 = 0; i1 < cellCount; i1++) {

                    mapKey.add(firstRow.getCell(i1).toString());
                }
            }

            //解析每一行数据，构成数据对象
            int rowStart = firstRowCount + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int j = rowStart; j < rowEnd; j++) {

                Row row = sheet.getRow(j);//获取对应的row对象

                if (row == null) {

                    continue;
                }

                Map<String, String> dataMap = new HashMap<>();
                //将每一行数据转化为一个Map对象
                dataMap = convertRowToData(row, cellCount, mapKey);
                dataList.add(dataMap);
            }
        }
        return dataList;
    }

    /**
     * 解析Excel文件中所有的表，返回数据对象 * * @param workbook * @return
     */
    public static List<Map<String, String>> analysisExcel(Workbook workbook) {

        List<Map<String, String>> dataList = new ArrayList<>();
        int sheetCount = workbook.getNumberOfSheets();//或取一个Excel中sheet数量
        for (int i = 0; i < sheetCount; i++) {


            Sheet sheet = workbook.getSheetAt(i);

            if (sheet == null) {

                continue;
            }
            int firstRowCount = sheet.getFirstRowNum();//获取第一行的序号
            Row firstRow = sheet.getRow(firstRowCount);
            int cellCount = firstRow.getLastCellNum();//获取列数

            List<String> mapKey = new ArrayList<>();

            //获取表头信息，放在List中备用
            if (firstRow == null) {

                logger.info("解析Excel失败，在第一行没有读取到任何数据！");
            } else {

                for (int i1 = 0; i1 < cellCount; i1++) {

                    mapKey.add(firstRow.getCell(i1).toString());
                }
            }

            //解析每一行数据，构成数据对象
            int rowStart = firstRowCount + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int j = rowStart; j < rowEnd; j++) {

                Row row = sheet.getRow(j);//获取对应的row对象

                if (row == null) {

                    continue;
                }

                Map<String, String> dataMap = new HashMap<>();
                //将每一行数据转化为一个Map对象
                dataMap = convertRowToData(row, cellCount, mapKey);
                dataList.add(dataMap);
            }
        }
        return dataList;
    }

    /**
     * 将每一行数据转化为一个Map对象 * * @param row 行对象 * @param cellCount 列数 * @param mapKey 表头Map * @return
     */
    public static Map<String, String> convertRowToData(Row row, int cellCount, List<String> mapKey) {

        if (mapKey == null) {

            logger.info("没有表头信息");
            return null;
        }
        Map<String, String> resultMap = new HashMap<>();
        Cell cell = null;
        for (int i = 0; i < cellCount; i++) {

            cell = row.getCell(i);
            if (cell == null) {

                resultMap.put(mapKey.get(i), "");
            } else {

                resultMap.put(mapKey.get(i), getCellVal(cell));
            }
        }
        return resultMap;
    }

    /**
     * 获取单元格的值 * * @param cel * @return
     */
    public static String getCellVal(Cell cel) {

        if (cel.getCellType() == CellType.STRING) {

            return cel.getRichStringCellValue().getString();
        }
        if (cel.getCellType() == CellType.NUMERIC) {

            return cel.getNumericCellValue() + "";
        }
        if (cel.getCellType() == CellType.BOOLEAN) {

            return cel.getBooleanCellValue() + "";
        }
        if (cel.getCellType() == CellType.FORMULA) {

            return cel.getCellFormula() + "";
        }
        return cel.toString();
    }

    /**
     * 读取Excel中文件的特定的表，返回String数据（不区分列的情况或者单列的情况） * * @param filePath 文件路径 * @param sheetName 表格名字（索引） * @return
     */
    public static List<String> readExcelString(String filePath, String sheetName) {

        Workbook workbook = null;
        List<String> resultList = new ArrayList<>();
        try {

            String fileType = filePath.substring(filePath.lastIndexOf("."));
            workbook = getWorkbook(filePath, fileType);
            if (workbook == null) {

                logger.info("获取workbook对象失败");
                return null;
            }
            resultList = analysisExcelString(workbook, sheetName);
            return resultList;
        } catch (Exception e) {

            logger.error("读取Excel文件失败" + filePath + "错误信息", e);
            return null;
        } finally {

            try {

                if (null != workbook) {

                    workbook.close();
                }
            } catch (Exception e) {

                logger.error("关闭数据流出错！错误信息：", e);
                return null;
            }

        }
    }

    /**
     * 解析Excel文件中特定的表，返回String数据（不区分列的情况或者单列的情况） * @param workbook * @param sheetName * @return
     */
    public static List<String> analysisExcelString(Workbook workbook, String sheetName) {

        List<String> resultList = new ArrayList<>();//获取的结果
        Sheet sheet = workbook.getSheet(sheetName);//获取的表
        int firstRowCount = sheet.getFirstRowNum();//获取第一行的序号
        Row firstRow = sheet.getRow(firstRowCount);
        int cellCount = firstRow.getLastCellNum();//获取列数

        //解析每一行数据，构成数据对象
        int rowStart = firstRowCount + 1;
        int rowEnd = sheet.getPhysicalNumberOfRows();
        for (int j = rowStart; j < rowEnd; j++) {

            Row row = sheet.getRow(j);//获取对应的row对象
            if (row == null) {

                continue;
            }
            Cell cell = null;
            for (int i = 0; i < cellCount; i++) {

                cell = row.getCell(i);
                if (cell == null) {


                } else {

                    resultList.add(getCellVal(cell));
                }
            }

        }
        return resultList;
    }

    /**
     * 创建新excel * * @param filePath excel的路径 * @param sheetName 表格名字（索引） * @param titleRow excel的第一行即表格头
     */
    public static Boolean createExcel(String filePath, String sheetName, String titleRow[]) {

        String fileType = filePath.substring(filePath.lastIndexOf("."));
        Workbook workbook = getWorkbook(filePath, fileType);
        if (workbook == null) {

            logger.info("获取workbook对象失败");
            return false;
        }
        //创建工作表
        Sheet sheet = workbook.createSheet(sheetName);
        System.out.println(sheet.getSheetName());
        //创建第一行，也就是表头
        Row row = sheet.createRow(0);
        Cell cell = null;
        for (int i = 0; i < titleRow.length; i++) {

            cell = row.createCell(i);
            cell.setCellValue(titleRow[i]);
        }
        //新建文件
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return true;
    }

    /**
     * 往excel中写入(已存在的数据无法写入). * * @param filePath 文件路径 * @param sheetName 表格名字（索引） * @param mapList 写入的数据
     */

    public static void writeToExcel(String filePath, String sheetName, List<Map<String, String>> mapList) {

        Workbook workbook = null;
        String fileType = filePath.substring(filePath.lastIndexOf("."));
        workbook = getWorkbook(filePath, fileType);
        if (workbook == null) {

            logger.info("获取workbook对象失败");
            return;
        }
// FileOutputStream fileOutputStream = null;
        //获取要写的工作表
        Sheet sheet = workbook.getSheet(sheetName);
        System.out.println("工作表：" + sheet);
        // 获得表头行对象
        Row titleRow = sheet.getRow(0);

        if (titleRow != null) {

            //获取表头的列数
            int columnCount = titleRow.getLastCellNum();
            //添加数据
            for (int rowId = 0; rowId < mapList.size(); rowId++) {

                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(rowId + 1);
                Map<String, String> map = mapList.get(rowId);
                System.out.println("传进去的值：" + map);
                //下面需要修改 列
                String a = map.get(titleRow.getCell(0).toString()); //获取列的数据
                String b = map.get(titleRow.getCell(1).toString());

                for (int colId = 0; colId < columnCount; colId++) {
                    // 在一行内循环
                    Cell first = row.createCell(0);//第一列
                    first.setCellValue(a);
                    Cell second = row.createCell(1);//第二列
                    second.setCellValue(b);
                }

            }
        }
        //文件流
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            workbook.write(fileOutputStream);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

// //读取文件夹，批量解析Excel文件
// System.out.println("--------------------读取文件夹，批量解析Excel文件-----------------------");
// List<Object> returnList = readFolder("C:\\Users\\Administrator\\Desktop\\ExcelTest");
// for (int i = 0; i < returnList.size(); i++) {

// List<Map<String, String>> maps = (List<Map<String, String>>) returnList.get(i);
// for (int j = 0; j < maps.size(); j++) {

// System.out.println(maps.get(j).toString());
// }
// System.out.println("--------------------手打List切割线-----------------------");
// }

        //读取单个文件
        System.out.println("--------------------读取并解析单个文件-----------------------");

        List<Map> maps1 = readExcel("D:\\workspace\\quality\\kangaroo\\kangaroo-common\\kangaroo-common-core\\src\\main\\java\\ink\\kangaroo\\common\\core\\utils\\sql\\1(1).xlsx", "temp (2)改");
//        List<Map<String, String>> maps1 = readExcel("D:\\workspace\\quality\\kangaroo\\kangaroo-common\\kangaroo-common-core\\src\\main\\java\\ink\\kangaroo\\common\\core\\utils\\sql\\1.xlsx","temp (2)改");
        List<Map> maps2 = readExcel("D:\\workspace\\quality\\kangaroo\\kangaroo-common\\kangaroo-common-core\\src\\main\\java\\ink\\kangaroo\\common\\core\\utils\\sql\\2(1).xlsx", "Sheet4");
//        List<Map<String, String>> maps2 = readExcel("D:\\workspace\\quality\\kangaroo\\kangaroo-common\\kangaroo-common-core\\src\\main\\java\\ink\\kangaroo\\common\\core\\utils\\sql\\2.xlsx","Sheet4");
        for (Map<String, String> map2 : maps2) {
//            String name2 = map2.get("项 目 名 称");
            String name2 = map2.get("C");
            String data2 = map2.get("N");
            String price2 = map2.get("O");
//            System.out.println(name2 + data2 + price2);
            boolean flag = true;
            for (Map<String, String> map1 : maps1) {
//            System.out.println(map1.get("项目名称"));
                String name1 = map1.get("B");
                String price1 = map1.get("E");
                if (StringUtils.isEmpty(name1)) break;
                if (name1.equals(name2)) {
                    map1.put("D", String.valueOf(Double.parseDouble(price2) * 10));
                    if (StringUtils.isEmpty(data2)) {
                        map1.put("E", String.valueOf(Double.parseDouble(price2) * 10));
                    } else {
                        String[] split = data2.split("-");
                        String datastart = split[0];
                        String dataend = split[1];
                        if (datastart.length() < 7) {
                            datastart = datastart.substring(0, 4) + 0 + datastart.charAt(5);
                        } else {
                            datastart = datastart.substring(0, 4) + datastart.substring(5, 7);
                        }
                        if (dataend.length() < 7) {
                            if (dataend.equals("长期")) {
                                map1.put("E", String.valueOf(Double.parseDouble(price2) * 10));
                            } else {
                                dataend = dataend.substring(0, 4) + 0 + dataend.charAt(5);
                            }
                        } else {
                            dataend = dataend.substring(0, 4) + dataend.substring(5, 7);
                        }
                        map1.put("C", datastart);
                        //判断结束时间
                        if (dataend.equals("长期")) {
                            break;
                        }
                        if (Integer.parseInt(dataend.substring(0, 4)) < 2022) {
                            map1.put("E", String.valueOf(Double.parseDouble(price2) * 10));
                        } else {
                            map1.put("E", String.valueOf(Double.parseDouble(price2) * 5));
                        }
                    }
                    flag = false;
                }
            }
            if (flag){
                Map<String,String> map = new HashMap<>();
                map.put("B",name2);
                map.put("D", String.valueOf(Double.parseDouble(price2) * 10));
                if (StringUtils.isEmpty(data2)) {
                    map.put("E", String.valueOf(Double.parseDouble(price2) * 10));
                } else {
                    String[] split = data2.split("-");
                    String datastart = split[0];
                    String dataend = split[1];
                    if (datastart.length() < 7) {
                        datastart = datastart.substring(0, 4) + 0 + datastart.charAt(5);
                    } else {
                        datastart = datastart.substring(0, 4) + datastart.substring(5, 7);
                    }
                    if (dataend.length() < 7) {
                        if (dataend.equals("长期")) {
                            map.put("E", String.valueOf(Double.parseDouble(price2) * 10));
                        } else {
                            dataend = dataend.substring(0, 4) + 0 + dataend.charAt(5);
                        }
                    } else {
                        dataend = dataend.substring(0, 4) + dataend.substring(5, 7);
                    }
                    map.put("C", datastart);
                    //判断结束时间
                    if (dataend.equals("长期")) {
                        break;
                    }
                    if (Integer.parseInt(dataend.substring(0, 4)) < 2022) {
                        map.put("E", String.valueOf(Double.parseDouble(price2) * 10));
                    } else {
                        map.put("E", String.valueOf(Double.parseDouble(price2) * 5));
                    }
                }
                maps1.add(map);
            }
//            map2.forEach((k,v)->{
//
//                System.out.println(k+"----"+v);
//            });
        }
//        int count = 0;
//        for (Map<String, String> stringStringMap : maps1) {
//            count++;
//            if (stringStringMap.get("项目名称") != null) {
//                System.out.println();
//            }
//        }
        List<Temp> temps = new ArrayList<>();
        for (Map map : maps1) {
            Temp temp = new Temp();
            String s = JSON.toJSONString(map);
            temp = JSON.parseObject(s, Temp.class);
            temps.add(temp);
        }
        ExcelUtil<Temp> mapExcelUtil = new ExcelUtil<>(Temp.class);
        mapExcelUtil.init(temps,"001", Excel.Type.EXPORT);
        mapExcelUtil.exportExcel(new FileOutputStream("D:\\logs\\1(2).xlsx"));

//        writeToExcel("D:\\logs\\1(2).xlsx", "temp (2)改", maps1);
    }

}
