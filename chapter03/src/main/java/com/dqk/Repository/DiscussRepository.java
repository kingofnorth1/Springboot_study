package com.dqk.Repository;

import com.dqk.domain.Discuss;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DiscussRepository extends JpaRepository<Discuss,Integer> {
    public List<Discuss> findByAuthorNotNull();
    @Query("select c from t_comment c where c.aId = ?1")
    public List<Discuss> getDiscussPaged(Integer aid, Pageable pageable);
    @Query(value="select c from t_comment c where c.aId = ?1",nativeQuery=true)
    public List<Discuss> getDiscussPaged2(Integer aid, Pageable pageable);
    @Transactional
    @Modifying
    @Query("update t_comment c set c.author=?1 where c.id=?2")
    public int updateDiscuss(String author,Integer id);
    @Transactional
    @Modifying
    @Query("delete from t_comment c where c.id=?1")
    public int deleteDiscuss(Integer id);
}
