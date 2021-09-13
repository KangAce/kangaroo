package ink.kangaroo.common.ekuaishou.model.adunit.param;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotNull;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 17:47
 */
public class Keyword {


    /**
     * 	关键词id
     * id与name需互相匹配
     * id
     */
    @NotNull
    @JsonAlias("id")
    private Long id;

    /**
     * 	关键词name
     * id与name需互相匹配
     * name
     */
    @NotNull
    @JsonAlias("name")
    private String name;

}
