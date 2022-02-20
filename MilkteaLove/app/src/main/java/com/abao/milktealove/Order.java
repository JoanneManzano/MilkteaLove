package com.abao.milktealove;

public class Order {

    public String id, flavor, size, addons, quantity, receiver, address, phone , price;

    public Order(String id, String flavor, String size, String addons, String quantity, String receiver, String address, String phone, String price) {
        this.id = id;
        this.flavor = flavor;
        this.size = size;
        this.addons = addons;
        this.quantity = quantity;
        this.receiver = receiver;
        this.address = address;
        this.phone = phone;
        this.price = price;
    }
    
    public Order() {

    }

    public String getId() {
        return id;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    public String getAddons() {
        return addons;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }
}
