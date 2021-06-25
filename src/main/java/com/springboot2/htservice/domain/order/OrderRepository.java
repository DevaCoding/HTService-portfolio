package com.springboot2.htservice.domain.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //mypage에서 본인의 매칭내역만 가져오는 쿼리문 만들어야 함.
    @Query("select o from Order o where o.user.id= :id order by o.id desc")
    Page<Order> findAllByUserOrderByIdDesc(@Param("id")Long id, Pageable pageable);



}
