import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;

@DisplayName("Lab 7: Character Generator (Constructors)")
public class CharacterTest {

    private Character hero;

    @BeforeEach
    void setUp() {
        hero = new Character("Aragorn");
    }

    // ==================== 🟢 CORE ====================

    @Test
    @Tag("core")
    @DisplayName("Character класс үүссэн эсэх")
    void classExists() {
        assertNotNull(hero);
    }

    @Test
    @Tag("core")
    @DisplayName("Гурван constructor бүгд зарлагдсан")
    void threeConstructorsExist() throws Exception {
        Constructor<Character> c1 = Character.class.getConstructor(String.class);
        Constructor<Character> c2 = Character.class.getConstructor(String.class, int.class, int.class);
        Constructor<Character> c3 = Character.class.getConstructor(Character.class);
        assertNotNull(c1, "Character(String) constructor байх ёстой");
        assertNotNull(c2, "Character(String, int, int) constructor байх ёстой");
        assertNotNull(c3, "Character(Character) copy constructor байх ёстой");
    }

    @Test
    @Tag("core")
    @DisplayName("Бүх талбар private эсэх")
    void allFieldsPrivate() {
        Field[] fields = Character.class.getDeclaredFields();
        assertTrue(fields.length >= 6, "Дор хаяж 6 талбар зарлагдсан байх ёстой");
        for (Field f : fields) {
            // Skip static fields (e.g. Random instance)
            if (Modifier.isStatic(f.getModifiers())) continue;
            assertTrue(Modifier.isPrivate(f.getModifiers()),
                f.getName() + " талбар private биш байна!");
        }
    }

    @Test
    @Tag("core")
    @DisplayName("Character(String) default статуудыг онооно")
    void defaultConstructorSetsDefaults() {
        Character h = new Character("Aragorn");
        assertEquals("Aragorn", h.getName());
        assertEquals(100, h.getHp());
        assertEquals(100, h.getMaxHp());
        assertEquals(50, h.getMp());
        assertEquals(50, h.getMaxMp());
        assertEquals(0, h.getGold());
    }

    @Test
    @Tag("core")
    @DisplayName("Character(String, int, int) захиалгат стат")
    void customConstructorSetsStats() {
        Character boss = new Character("Gandalf", 200, 300);
        assertEquals("Gandalf", boss.getName());
        assertEquals(200, boss.getHp());
        assertEquals(200, boss.getMaxHp());
        assertEquals(300, boss.getMp());
        assertEquals(300, boss.getMaxMp());
        assertEquals(0, boss.getGold());
    }

    @Test
    @Tag("core")
    @DisplayName("Copy constructor бүх талбарыг хуулна")
    void copyConstructorCopiesAll() {
        Character original = new Character("Aragorn", 150, 80);
        Character clone = new Character(original);
        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getHp(), clone.getHp());
        assertEquals(original.getMaxHp(), clone.getMaxHp());
        assertEquals(original.getMp(), clone.getMp());
        assertEquals(original.getMaxMp(), clone.getMaxMp());
        assertEquals(original.getGold(), clone.getGold());
    }

    @Test
    @Tag("core")
    @DisplayName("Copy constructor шинэ объектыг буцаана")
    void copyConstructorReturnsNewObject() {
        Character original = new Character("Aragorn", 150, 80);
        Character clone = new Character(original);
        assertNotSame(original, clone, "Copy constructor нь шинэ reference буцаах ёстой");
    }

    @Test
    @Tag("core")
    @DisplayName("Getter-үүд зөв утгатай")
    void gettersReturnCorrectly() throws Exception {
        Character h = new Character("Bob", 120, 60);
        // Reflection-оор талбарыг уншиж getter-тай харьцуулна
        Field hp = Character.class.getDeclaredField("hp");
        Field mp = Character.class.getDeclaredField("mp");
        Field gold = Character.class.getDeclaredField("gold");
        hp.setAccessible(true);
        mp.setAccessible(true);
        gold.setAccessible(true);
        assertEquals(hp.getInt(h), h.getHp());
        assertEquals(mp.getInt(h), h.getMp());
        assertEquals(gold.getInt(h), h.getGold());
    }

    @Test
    @Tag("core")
    @DisplayName("Character(String) ба Character(String,100,50) ижил үр дүнтэй (chaining)")
    void chainingProducesEquivalentObject() {
        Character a = new Character("X");
        Character b = new Character("X", 100, 50);
        assertEquals(a.getName(), b.getName());
        assertEquals(a.getHp(), b.getHp());
        assertEquals(a.getMaxHp(), b.getMaxHp());
        assertEquals(a.getMp(), b.getMp());
        assertEquals(a.getMaxMp(), b.getMaxMp());
        assertEquals(a.getGold(), b.getGold());
    }

    // ==================== 🟡 STRETCH ====================

    @Test
    @Tag("stretch")
    @DisplayName("createWarrior HP 150, MP 20")
    void createWarriorStats() throws Exception {
        Method m = Character.class.getMethod("createWarrior", String.class);
        assertTrue(Modifier.isStatic(m.getModifiers()), "createWarrior нь static байх ёстой");
        Character w = (Character) m.invoke(null, "Conan");
        assertEquals("Conan", w.getName());
        assertEquals(150, w.getHp());
        assertEquals(150, w.getMaxHp());
        assertEquals(20, w.getMp());
        assertEquals(20, w.getMaxMp());
    }

    @Test
    @Tag("stretch")
    @DisplayName("createMage HP 80, MP 120")
    void createMageStats() throws Exception {
        Method m = Character.class.getMethod("createMage", String.class);
        assertTrue(Modifier.isStatic(m.getModifiers()), "createMage нь static байх ёстой");
        Character mg = (Character) m.invoke(null, "Merlin");
        assertEquals("Merlin", mg.getName());
        assertEquals(80, mg.getHp());
        assertEquals(80, mg.getMaxHp());
        assertEquals(120, mg.getMp());
        assertEquals(120, mg.getMaxMp());
    }

    @Test
    @Tag("stretch")
    @DisplayName("random баатрын HP/MP зөв мужид оршино")
    void randomCharacterInRange() throws Exception {
        Method m = Character.class.getMethod("random", String.class);
        assertTrue(Modifier.isStatic(m.getModifiers()), "random нь static байх ёстой");
        for (int i = 0; i < 50; i++) {
            Character r = (Character) m.invoke(null, "???");
            assertEquals("???", r.getName());
            assertTrue(r.getHp() >= 50 && r.getHp() <= 150,
                "HP " + r.getHp() + " нь 50..150 мужид байх ёстой");
            assertTrue(r.getMp() >= 20 && r.getMp() <= 100,
                "MP " + r.getMp() + " нь 20..100 мужид байх ёстой");
            assertEquals(0, r.getGold());
        }
    }

    // ==================== 🔴 BONUS ====================

    @Test
    @Tag("bonus")
    @DisplayName("CharacterBuilder класс үүссэн")
    void characterBuilderExists() {
        assertDoesNotThrow(() -> {
            Class.forName("CharacterBuilder");
        }, "CharacterBuilder класс байх ёстой");
    }

    @Test
    @Tag("bonus")
    @DisplayName("CharacterBuilder fluent chaining ажиллана")
    void characterBuilderFluent() throws Exception {
        Class<?> builderClass = Class.forName("CharacterBuilder");
        Object builder = builderClass.getDeclaredConstructor().newInstance();

        // .name("Bob").hp(120).mp(50).gold(100).build()
        Method nameM = builderClass.getMethod("name", String.class);
        Method hpM = builderClass.getMethod("hp", int.class);
        Method mpM = builderClass.getMethod("mp", int.class);
        Method goldM = builderClass.getMethod("gold", int.class);
        Method buildM = builderClass.getMethod("build");

        Object b = nameM.invoke(builder, "Bob");
        assertNotNull(b, "name() нь this буцаах ёстой");
        b = hpM.invoke(b, 120);
        b = mpM.invoke(b, 50);
        b = goldM.invoke(b, 100);

        Character hero = (Character) buildM.invoke(b);
        assertEquals("Bob", hero.getName());
        assertEquals(120, hero.getHp());
        assertEquals(50, hero.getMp());
        assertEquals(100, hero.getGold());
    }
}
