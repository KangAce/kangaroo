package ink.kangaroo.pixiv.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Base repository interface contains some common methods.
 *
 * @param <DOMAIN> doamin type
 * @param <ID>     id type
 * @author kang
 * @date 2019-03-15
 */
@NoRepositoryBean
@Repository
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID>, JpaSpecificationExecutor<DOMAIN>  {


    /**
     * Finds all domain by id list.
     *
     * @param ids  id list of domain must not be null
     * @param sort the specified sort must not be null
     * @return a list of domains
     */
    @NonNull
    List<DOMAIN> findAllByIdIn(@NonNull Collection<ID> ids, @NonNull Sort sort);

    /**
     * Finds all domain by domain id list.
     *
     * @param ids      must not be null
     * @param pageable must not be null
     * @return a list of domains
     */
    @NonNull
    Page<DOMAIN> findAllByIdIn(@NonNull Collection<ID> ids, @NonNull Pageable pageable);

    /**
     * Deletes by id list.
     *
     * @param ids id list of domain must not be null
     * @return number of rows affected
     */
    long deleteByIdIn(@NonNull Collection<ID> ids);

}
