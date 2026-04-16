# Lab 7 — Character Generator (Constructors)

**Нийт оноо:** 100 | **Сэдэв:** Constructor overloading, `this(...)` chaining, copy constructor, static factory methods

## 🎭 Түүх

Dungeon of OOP-ын хаалга нээгдэж, шинэ адвенчурерууд цувран орж байна. Нэг нь зөвхөн **нэрээ** хэлээд үндсэн статаар эхэлмээр байна, нөгөө нь **өөрийн HP/MP**-ээ сонгоё гэнэ, гурав дахь нь **хуучин баатрынхаа хуулбарыг** дуудмаар байна. Чиний үүрэг — `Character` класст **3 өөр constructor** бичиж, `this(...)` chaining ашиглан код давтагдахаас сэргийлэх.

Үүнээс гадна дурсгалт баатрын анги — `Warrior`, `Mage` — болон санамсаргүй баатрыг `static` **factory method**-оор үүсгэнэ.

---

## 📋 Шаардлагатай Талбарууд (Fields)

**Бүгд `private`.** Шууд гаднаас хандах боломжгүй байх ёстой.

| Талбар | Төрөл | Тайлбар |
|--------|--------|---------|
| `name` | `String` | Баатрын нэр |
| `hp` | `int` | Одоогийн амьдралын оноо |
| `maxHp` | `int` | Дээд амьдрал |
| `mp` | `int` | Одоогийн ид шидийн оноо |
| `maxMp` | `int` | Дээд ид шид |
| `gold` | `int` | Хуримтлуулсан алт |

---

## 🟢 Core (60 оноо)

### 1. `Character(String name)` — default constructor

- `name` талбарт параметрын утгыг өгнө
- Default статууд: `hp = 100`, `maxHp = 100`, `mp = 50`, `maxMp = 50`, `gold = 0`
- **Энэ constructor нь `this(name, 100, 50)`-г дуудна** (chaining)

**Жишээ:**
```java
Character hero = new Character("Aragorn");
// hero.getHp() == 100, hero.getMp() == 50, hero.getGold() == 0
```

### 2. `Character(String name, int hp, int mp)` — custom stats

- `name`, `hp`, `mp`-ийг параметрээс авна
- `maxHp = hp`, `maxMp = mp` (эхлэлийн утга нь дээд утга нь болно)
- `gold = 0`

**Жишээ:**
```java
Character boss = new Character("Gandalf", 200, 300);
// boss.getHp() == 200, boss.getMaxHp() == 200
// boss.getMp() == 300, boss.getMaxMp() == 300
// boss.getGold() == 0
```

### 3. `Character(Character other)` — copy constructor

- Өөр `Character`-ын бүх талбарыг хуулна
- Шинээр үүссэн объект нь `other`-ээс **бие даасан** (deep copy хэрэггүй, учир нь талбар бүгд primitive/String immutable)

**Жишээ:**
```java
Character original = new Character("Aragorn", 150, 80);
Character clone = new Character(original);
// clone.getName() == "Aragorn"
// clone.getHp() == 150
// clone.getMp() == 80
```

### 4. `this(...)` chaining дүрэм

- Constructor дотор `this(...)` дуудах бол **эхний мөр** байх ёстой
- `Character(String name)` нь `this(name, 100, 50)`-г дуудна
- `Character(String name, int hp, int mp)` нь **бусад constructor руу chaining хийх шаардлагагүй** — талбаруудыг шууд тавьж болно

### 5-11. Getter-ууд

- `getName() → String`
- `getHp() → int`
- `getMaxHp() → int`
- `getMp() → int`
- `getMaxMp() → int`
- `getGold() → int`

---

## 🟡 Stretch (30 оноо)

### 12. `static Character createWarrior(String name)`

Warrior анги — хүчтэй, бие бялдартай:

- `hp = 150`, `mp = 20`
- Буцаах: шинэ `Character` объект

**Жишээ:**
```java
Character conan = Character.createWarrior("Conan");
// conan.getHp() == 150, conan.getMp() == 20
```

### 13. `static Character createMage(String name)`

Mage анги — сул бие, өндөр ид шид:

- `hp = 80`, `mp = 120`
- Буцаах: шинэ `Character` объект

**Жишээ:**
```java
Character merlin = Character.createMage("Merlin");
// merlin.getHp() == 80, merlin.getMp() == 120
```

### 14. `static Character random(String name)`

Санамсаргүй баатар — HP, MP санамсаргүй муж дотор:

- `hp` — 50..150 хооронд (хоёр талдаа оролцоно)
- `mp` — 20..100 хооронд (хоёр талдаа оролцоно)
- `gold = 0`
- `java.util.Random` ашиглаж болно

**Жишээ:**
```java
Character r = Character.random("???");
// 50 <= r.getHp() <= 150
// 20 <= r.getMp() <= 100
```

---

## 🔴 Bonus (10 оноо)

### 15. `CharacterBuilder` — fluent builder

Нэг файл дотор (`Character.java`) доор нэмэлт класс зарлана:

```java
class CharacterBuilder {
    // fluent method: .name(...), .hp(...), .mp(...), .gold(...)
    // build() → Character буцаана
}
```

- Бүх setter method нь `this` буцаана (chain хийх боломжтой)
- `build()` нь хуримтлуулсан утгуудтай `Character` буцаана
- Анхны default утгууд: `name = "Hero"`, `hp = 100`, `mp = 50`, `gold = 0`

**Жишээ:**
```java
Character hero = new CharacterBuilder()
    .name("Bob")
    .hp(120)
    .mp(50)
    .gold(100)
    .build();

// hero.getName() == "Bob"
// hero.getHp() == 120
// hero.getGold() == 100
```

---

## 🧪 Тест ажиллуулах

```bash
# Бүх tier
bash scripts/run_tests.sh

# Зөвхөн core
bash scripts/run_tests.sh --tag core

# Зөвхөн stretch
bash scripts/run_tests.sh --tag stretch

# Зөвхөн bonus
bash scripts/run_tests.sh --tag bonus
```

---

## ✅ Шалгуурын жагсаалт (Checklist)

### Core
- [ ] 6 талбар бүгд `private`
- [ ] `Character(String name)` constructor зөв ажиллах
- [ ] `Character(String name, int hp, int mp)` constructor зөв ажиллах
- [ ] `Character(Character other)` copy constructor зөв ажиллах
- [ ] `this(...)` chaining ашиглагдсан эсэх
- [ ] 6 getter бүгд зөв буцаах утгатай

### Stretch
- [ ] `createWarrior` static factory — HP 150, MP 20
- [ ] `createMage` static factory — HP 80, MP 120
- [ ] `random` static factory — HP 50..150, MP 20..100

### Bonus
- [ ] `CharacterBuilder` класс үүссэн
- [ ] Fluent chaining ажиллана (`.name().hp().build()`)

---

## 🚫 Түгээмэл алдаанууд

1. **`this(...)` chaining эхний мөр байхгүй** — Java compile алдаа өгнө
2. **Copy constructor дотор зөвхөн `name` хуулж, бусдыг орхих** — бүх 6 талбарыг хуулна
3. **`static` keyword орхих** — `createWarrior`, `createMage`, `random`-д заавал `static` бичнэ
4. **`Random` seed hardcode хийх** — тест санамсаргүй утгыг зөвхөн муж дотор шалгана, яг утгыг биш
5. **`CharacterBuilder.build()` нь `null` буцаах** — заавал шинэ `Character` объект буцаана
6. **Tests өөрчлөх** — `tests/` хавтсыг хөндөхгүй, тэгвэл PR бүтэлгүйтнэ
