package com.tilldawn.Model.enums;

public enum Avatar {
    Hastur("Images_grouped_1/Sprite/Avatar/T_Hastur_Portrait.png"),
    Hina("Images_grouped_1/Sprite/Avatar/T_Hina_Portrait.png"),
    Luna("Images_grouped_1/Sprite/Avatar/T_Luna_Portrait.png"),
    Raven("Images_grouped_1/Sprite/Avatar/T_Raven_Portrait.png"),
    Yuki("Images_grouped_1/Sprite/Avatar/T_Yuki_Portrait.png"),
    Spark("Images_grouped_1/Sprite/Avatar/T_Spark_Portrait.png");

    private final String source;

    Avatar(String source) {
        this.source = source;

    }

    public String getSource() {
        return source;
    }

}
