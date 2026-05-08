package assignments.character;

import java.util.Random;

public class Character {

    // ─────── Талбарууд (Fields) ───────
    private final String name;
    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int gold;

    // ─────── 🟢 Core (60 оноо) ───────

    public Character(String name) {
        this(name, 100, 50);
    }

    public Character(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.mp = mp;
        this.maxMp = mp;
        this.gold = 0;
    }

    public Character(Character other) {
        this.name = other.name;
        this.hp = other.hp;
        this.maxHp = other.maxHp;
        this.mp = other.mp;
        this.maxMp = other.maxMp;
        this.gold = other.gold;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMp() { return mp; }
    public int getMaxMp() { return maxMp; }
    public int getGold() { return gold; }

    // ─────── 🟡 Stretch (30 оноо) ───────

    public static Character createWarrior(String name) {
        return new Character(name, 150, 20);
    }

    public static Character createMage(String name) {
        return new Character(name, 80, 120);
    }

    public static Character random(String name) {
        Random rand = new Random();
        // 50..150 -> rand.nextInt(150 - 50 + 1) + 50
        int randomHp = rand.nextInt(101) + 50;
        // 20..100 -> rand.nextInt(100 - 20 + 1) + 20
        int randomMp = rand.nextInt(81) + 20;
        return new Character(name, randomHp, randomMp);
    }

    // Builder-т зориулсан setter (Bonus хэсэгт хэрэгтэй)
    private void setGold(int gold) { this.gold = gold; }
    private void setHp(int hp) { this.hp = hp; this.maxHp = hp; }
    private void setMp(int mp) { this.mp = mp; this.maxMp = mp; }
}

// ─────── 🔴 Bonus (10 оноо) ───────

class CharacterBuilder {
    private String name = "Hero";
    private int hp = 100;
    private int mp = 50;
    private int gold = 0;

    public CharacterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder hp(int hp) {
        this.hp = hp;
        return this;
    }

    public CharacterBuilder mp(int mp) {
        this.mp = mp;
        return this;
    }

    public CharacterBuilder gold(int gold) {
        this.gold = gold;
        return this;
    }

    public Character build() {
        Character c = new Character(name, hp, mp);
        // Gold-ыг тусад нь оноох (Учир нь конструктөр 0 болгодог)
        // Гэхдээ Character класс дотор setter эсвэл Reflection ашиглаж болно.
        // Энд шууд утгыг нь оноохын тулд Character дотор туслах функц ашиглав.
        try {
            java.lang.reflect.Field goldField = Character.class.getDeclaredField("gold");
            goldField.setAccessible(true);
            goldField.set(c, this.gold);
        } catch (Exception e) {
            // Reflection ашиглахгүй бол Character класс дотор gold-ыг 
            // оноодог нэмэлт логик хэрэгтэй болно.
        }
        return c;
    }
}