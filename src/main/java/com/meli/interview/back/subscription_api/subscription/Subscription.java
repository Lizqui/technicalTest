package com.meli.interview.back.subscription_api.subscription;

/*Aquí había un bug, ya que en la última condición si no es spotify queda el valor en 0. 
Ignorando las otras condiciones */

public class Subscription {

    private String partner;

    public Subscription(String partner) {
        this.partner = partner;
    }

    public float getPrice() {
        float price = 0;
        switch (partner) {
            case "disney":
                price = 100;    
                break;
            case "netflix":
                price = 200;
                break;
            case "spotify":         
                price = 50;
                break;                                
            default:
                price = 0;
                break;
        }                
        return price;
     }
}
