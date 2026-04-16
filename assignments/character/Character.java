public class Character {

    // TODO: private талбаруудыг зарлана уу
    // - name (String)
    // - hp (int)
    // - maxHp (int)
    // - mp (int)
    // - maxMp (int)
    // - gold (int)

    // ─────── 🟢 Core (60 оноо) ───────

    // TODO: Constructor 1 — public Character(String name)
    // - this(name, 100, 50)-г эхний мөрөнд дуудна (chaining)
    // - Өөр юу ч хийх шаардлагагүй

    // TODO: Constructor 2 — public Character(String name, int hp, int mp)
    // - name, hp, mp-ийг параметрээс талбарт онооно
    // - maxHp = hp, maxMp = mp
    // - gold = 0

    // TODO: Constructor 3 (copy) — public Character(Character other)
    // - other объектын бүх талбарыг хуулна
    // - Жишээ: this.name = other.name; this.hp = other.hp; ...

    // TODO: getName() → String

    // TODO: getHp() → int

    // TODO: getMaxHp() → int

    // TODO: getMp() → int

    // TODO: getMaxMp() → int

    // TODO: getGold() → int

    // ─────── 🟡 Stretch (30 оноо) ───────

    // TODO: public static Character createWarrior(String name)
    // - new Character(name, 150, 20) буцаана

    // TODO: public static Character createMage(String name)
    // - new Character(name, 80, 120) буцаана

    // TODO: public static Character random(String name)
    // - java.util.Random ашиглан:
    //   hp — 50..150 (хоёр талдаа оролцоно)
    //   mp — 20..100 (хоёр талдаа оролцоно)
    // - new Character(name, hp, mp) буцаана
}

// ─────── 🔴 Bonus (10 оноо) ───────

// TODO: class CharacterBuilder { ... }
// - Fluent builder: .name(String), .hp(int), .mp(int), .gold(int)
// - Тус бүр нь this-ийг буцаана
// - build() нь Character объект буцаана
// - Анхны утга: name="Hero", hp=100, mp=50, gold=0
