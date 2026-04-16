# 🐉 Lab 7 — Constructor: Character Generator

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![JUnit](https://img.shields.io/badge/JUnit-5-green?logo=junit5)
![Auto-Grader](https://img.shields.io/badge/Auto--Grader-Enabled-blue)
![AI Detection](https://img.shields.io/badge/AI%20Detection-Enabled-red)

> Dungeon of OOP-ын хаалга тогшиж, шинэ адвенчурерууд нэг нэгээрээ оролцож байна. Гэвч баатар бүр адилхан төрдөггүй — зарим нь зөвхөн нэрээрээ, зарим нь өөрийн онцгой статистаар, бүр зарим нь хуучин баатрын **хуулбар** болон сэргэдэг. Энэ лабораторт чи `Character` классын **олон constructor** бичиж, `this(...)` chaining ашиглаж, **copy constructor** хийж сурна. Мөн **static factory method**-аар `Warrior`, `Mage` гэсэн тусгай анги-тай баатруудыг төрүүлнэ.

## 📚 Суралцах материал

- **Теори:** [`UEFA-OPP-resources/docs/week-07-constructors/`](https://github.com/UEFA-OPP/UEFA-OPP-resources/tree/main/docs/week-07-constructors)
- **Git workflow заавар:** [`UEFA-OPP-resources/docs/git-workflow/`](https://github.com/UEFA-OPP/UEFA-OPP-resources/tree/main/docs/git-workflow)

## 🏗️ Хавтасны бүтэц

```
lab7-template/
├── README.md                          # Энэ файл
├── .gitignore
├── assignments/
│   └── character/
│       ├── Character.java             # ← Та энд код бичнэ
│       └── README.md                  # Даалгаврын дэлгэрэнгүй заавар
├── tests/
│   └── CharacterTest.java             # JUnit 5 тестүүд (бүү өөрчил)
├── scripts/
│   ├── run_tests.sh                   # Тест ажиллуулах скрипт
│   └── ai_detector.py                 # AI илрүүлэгч
└── .github/workflows/grade.yml        # GitHub Actions автомат шалгагч
```

## 🚀 Лаб хийх заавар (Алхам алхмаар)

### Алхам 1: Repo-г Fork хийх

1. Браузераар [`UEFA-OPP/lab7-template`](https://github.com/UEFA-OPP/lab7-template) руу орно
2. Баруун дээд буланд **Fork** товч дарна
3. Owner-ээр өөрийн account-ийг сонгоод **Create fork** дарна
4. Одоо `https://github.com/<таны-username>/lab7-template` гэсэн хуулбартай боллоо

### Алхам 2: Компьютер дээрээ Clone хийх

```bash
git clone https://github.com/<таны-username>/lab7-template.git
cd lab7-template
```

> SSH key тохируулсан бол `git@github.com:<таны-username>/lab7-template.git` ашиглаж болно.

### Алхам 3: Өөрийн нэрээр branch үүсгэх

```bash
# Жишээ: git checkout -b lab7/bat-erdene
git checkout -b lab7/<өөрийн-нэр>
```

> **Яагаад branch вэ?** `main` branch-д шууд push хийвэл PR үүсгэх боломжгүй. Заавал шинэ branch дээр ажиллана.

### Алхам 4: Даалгаврын зааврыг унших

```bash
cat assignments/character/README.md
```

Энд `Character` классад ямар constructor, static factory method шаардлагатайг дэлгэрэнгүй бичсэн байгаа. `this(...)` chaining-ийн дүрэм, copy constructor-ийн логик, random баатар үүсгэх зэргийг анхааралтай унш.

### Алхам 5: Код бичих

`assignments/character/Character.java` файл дахь бүх `// TODO` комментыг өөрийн кодоор соль. Ядаж дараах түвшнүүдийг хийж үзнэ үү:

- 🟢 **Core (60 оноо)** — 3 constructor, `this(...)` chaining, copy constructor, getter-үүд
- 🟡 **Stretch (30 оноо)** — `createWarrior`, `createMage`, `random` static factory method
- 🔴 **Bonus (10 оноо)** — fluent `CharacterBuilder` класс

### Алхам 6: Локал тест ажиллуулах

```bash
# Бүх тестийг ажиллуулах
bash scripts/run_tests.sh

# Тодорхой tier дангаар шалгах
bash scripts/run_tests.sh --tag core
bash scripts/run_tests.sh --tag stretch
bash scripts/run_tests.sh --tag bonus
```

**Жишээ output:**
```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  Lab 7: Character Generator
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
[core]    ✓ 9/9 tests passed  → 60.0/60
[stretch] ✓ 3/3 tests passed  → 30.0/30
[bonus]   △ 1/2 tests passed  → 5.0/10
─────────────────────────────────────
НИЙТ ОНОО: 95.0 / 100
```

### Алхам 7: Commit хийх

```bash
git add assignments/
git commit -m "Implement Character constructors - <your name>"
```

> **Анхаар:** `tests/`, `scripts/`, `.github/` хавтсуудыг өөрчлөх/commit хийх хэрэггүй. Зөвхөн `assignments/character/Character.java` файлаа илгээнэ.
>
> Commit message-ийг **англиар** бичнэ (course convention).

### Алхам 8: GitHub руу Push хийх

```bash
git push origin lab7/<өөрийн-нэр>
```

### Алхам 9: Pull Request (PR) үүсгэх

1. `https://github.com/<таны-username>/lab7-template` руу орно
2. Шар өнгийн **"Compare & pull request"** товч дарна
3. Товч байхгүй бол: **Pull requests** → **New pull request**
   - **base repository:** `UEFA-OPP/lab7-template` | **base:** `main`
   - **head repository:** `<таны-username>/lab7-template` | **compare:** `lab7/<өөрийн-нэр>`
4. PR title-д **өөрийн нэр, бүлгийг** бичнэ. Жишээ: `Bat-Erdene - SE401`
5. **Create pull request** дарна

### Алхам 10: Автомат шалгалтын дүнг харах

PR үүсгэсний дараа GitHub Actions автоматаар ажиллана:

1. PR хуудасны доод талд **Checks** хэсэг гарна
2. ⏳ = ажиллаж байна | ✅ = амжилттай | ❌ = алдаатай
3. **Details** дарж дэлгэрэнгүй дүнг харна
4. PR-т автоматаар коммент бичигдэнэ:

| Tier | Tests | Score |
|------|-------|-------|
| 🟢 Core | 9/9 | 60.0 / 60 |
| 🟡 Stretch | 3/3 | 30.0 / 30 |
| 🔴 Bonus | 1/2 | 5.0 / 10 |
| **Total** | | **95.0 / 100** |
| AI Detection | | ✅ LOW (6) |

> **Алдаатай бол?** Кодоо засаад дахин commit + push хийнэ. PR автоматаар шинэчлэгдэж, тест дахин ажиллана.

## 📊 Оноо тооцох систем

| Tier | Жин | Тайлбар |
|------|-----|---------|
| 🟢 **Core** | **60%** | 3 constructor, `this(...)` chaining, copy constructor |
| 🟡 **Stretch** | **30%** | `createWarrior`, `createMage`, `random` static factory |
| 🔴 **Bonus** | **10%** | Fluent `CharacterBuilder` класс |

**Формула:**
```
score = (core_passed / core_total) * 60
      + (stretch_passed / stretch_total) * 30
      + (bonus_passed / bonus_total) * 10
```

## 🤖 AI Detection policy

AI detector кодын 11 шалгуурыг шинжилж оноо өгнө (0-121):

| Оноо | Түвшин | Үр дагавар |
|------|--------|------------|
| 0-19 | ✅ **LOW** | Асуудалгүй. Сайн! |
| 20-39 | ⚠️ **MEDIUM** | Багш кодыг шалгана. Хариулт хүсч магадгүй. |
| 40+ | 🚨 **HIGH** | Онооноос **50% хасна**. Повторный submission шаардлагатай. |

Шалгуурууд:
1. Javadoc хэт их хэрэглэсэн эсэх
2. `@param`/`@return` tag-ууд
3. Коммент/код харьцаа
4. AI-д түгээмэл хэллэгүүд ("This method...", "Returns the...")
5. AI-ийн түгээмэл коммент загвар ("// Getters and Setters")
6. 4 spacer-ын жигд indent
7. Мөрийн уртын жигд байдал
8. `throw new Exception` олон удаа
9. Хоосон мөрийн жигд зай
10. Method нэрийн жигд байдал
11. Ашиглагдаагүй import

## ⚠️ Дүрэм

1. **Тест файлыг өөрчлөхгүй** — `tests/CharacterTest.java`-г хөндөхгүй
2. **Зөвхөн `Character.java`-д код бичнэ**
3. **AI ашиглахгүй** — ChatGPT, Copilot, Claude, Gemini зэргийг хэрэглэхгүй
4. **Өөрийн branch дээр ажиллана** (`main` биш)
5. **Deadline-аа баримтална** — хожимдуулсан submission оноо хасагдана
6. **Commit message, код — англиар** | **Коммент — англи/монгол хамаагүй**

## 🛠️ Шаардлага

- **Java 17+** — `java -version` гэж шалгана
- **Python 3.11+** — `python3 --version` (AI detector ажиллуулахад)
- **Bash** — тест скрипт ажиллуулахад
- **curl** — JUnit jar автомат татахад
- **Git** — clone, commit, push хийхэд

## 📞 Асуулт байвал

Багшаасаа асуу. Discord / classroom channel-аар бичиж болно. Амжилт хүсье, адвенчурер! 🗡️🛡️
