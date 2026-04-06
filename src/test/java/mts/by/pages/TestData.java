package mts.by.utils;

public class TestData {

    public static final String EXPECTED_BLOCK_TITLE = "Онлайн пополнение без комиссии";

    public static final String[] EXPECTED_PAYMENT_OPTIONS = {
            "Услуги связи",
            "Домашний интернет",
            "Рассрочка",
            "Задолженность"
    };

    public static final String[] EXPECTED_PLACEHOLDERS = {
            "Номер телефона",
            "Номер абонента",
            "Номер договора",
            "Номер лицевого счета"
    };

    public static final String TEST_PHONE_NUMBER = "297777777";
    public static final String TEST_AMOUNT = "10.00";

    public static final String[] EXPECTED_CARD_FIELDS = {
            "Номер карты",
            "Срок действия",
            "CVC",
            "Имя держателя"
    };
}