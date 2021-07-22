package com.yahma.movie_review.repository.review;

import java.util.List;

import com.yahma.movie_review.entity.review.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m "
         + "left outer join MovieImage mi on mi.movie = m "
         + "left outer join Review r on r.movie = m group by m")
    // max(mi) 때문에 한 영화에 대한 지정된 개수만큼 이미지를 가져오느라 성능 저하 발생
    // @Query("select m, max(mi), avg(coalesce(r.grade, 0)), count(distinct r) from Movie m "
    //      + "left outer join MovieImage mi on mi.movie = m "
    //      + "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    // 지정한 영화 번호에 해당하는 영화 - 영화 이미지 정보 가져오기
    // @Query("select m, mi from Movie m "
    //      + "left outer join MovieImage mi on mi.movie = m where m.mno = :mno")
    // 리뷰 정보 추가
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) from Movie m "
         + "left outer join MovieImage mi on mi.movie = m "
         + "left outer join Review r on r.movie = m "
         + "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno);
}
