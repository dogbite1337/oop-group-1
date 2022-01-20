package com.company.Entities;

public class Subscriber {
    Integer subscriptionId, subscribedToId, subscriberId;

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Integer getSubscribedToId() {
        return subscribedToId;
    }

    public void setSubscribedToId(Integer subscribedToId) {
        this.subscribedToId = subscribedToId;
    }

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriptionId=" + subscriptionId +
                ", subscribedToId=" + subscribedToId +
                ", subscriberId=" + subscriberId +
                '}';
    }

    public Subscriber(Integer subscriptionId, Integer subscribedToId, Integer subscriberId) {
        this.subscriptionId = subscriptionId;
        this.subscribedToId = subscribedToId;
        this.subscriberId = subscriberId;
    }

}
