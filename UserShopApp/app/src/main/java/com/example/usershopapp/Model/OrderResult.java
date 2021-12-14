package com.example.usershopapp.Model;

public class OrderResult {
    public int OrderId;
    public String OrderDate;
    public int OrderStatus;
    public float OrderPrice;
    public String OrderDetail,OrderComment,OrderAddress,UserEmail;

    public OrderResult() {
    }

    public OrderResult(int orderId, String orderDate, int orderStatus, float orderPrice, String orderDetail, String orderComment, String orderAddress, String userEmail) {
        OrderId = orderId;
        OrderDate = orderDate;
        OrderStatus = orderStatus;
        OrderPrice = orderPrice;
        OrderDetail = orderDetail;
        OrderComment = orderComment;
        OrderAddress = orderAddress;
        UserEmail = userEmail;
    }

    public int getOrderId() {
        return OrderId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public float getOrderPrice() {
        return OrderPrice;
    }

    public String getOrderDetail() {
        return OrderDetail;
    }

    public String getOrderComment() {
        return OrderComment;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public String getUserEmail() {
        return UserEmail;
    }
}
