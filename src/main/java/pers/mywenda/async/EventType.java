package pers.mywenda.async;

public enum EventType {
    LIKE(0),//点赞
    COMMENT(1),//评论
    LOGIN(2),
    MAIL(3),
    FOLLOW(4),
    UNFOLLOW(5),
    ADD_QUESTION(6);
    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
