package com.group.myshop.service.order;

import com.group.myshop.domain.entity.member.Member;
import com.group.myshop.domain.entity.member.MemberRepository;
import com.group.myshop.domain.entity.order.Order;
import com.group.myshop.domain.entity.order.OrderRepository;
import com.group.myshop.domain.entity.order.ShippingInfo;
import org.springframework.transaction.annotation.Transactional;

public class ChangeOrderService {
    private OrderRepository orderRepository;
    private MemberRepository memberRepository;

    @Transactional
    public void changeShippingInfo(OrderId id, ShippingInfo newShippingInfo, boolean useNewShippingAddrAsMemberAddr) {
        Order order = orderRepository.findById(id);
        if (order == null) throw new OrderNotFoundException();
        order.changeShippingInfo(newShippingInfo);
        if (useNewShippingAddrAsMemberAddr) {
            Member member = memberRepository.findById(
                    order.getOrderer().getMemberId()
            );
            member.changeAddress(newShippingInfo.getAddress());
        }
    }
}
