package sample;
import org.junit.Test;
import static org.junit.Assert.*;

public class FullGradeCalculatorTests {
    /*@Test
    public void testARange() {
        Main m = new Main();
        //test sample A+ scores
        Main.FullGradeCalculator aPlus = m.new FullGradeCalculator();
        aPlus.updateHW(256);
        aPlus.updateVitamins(48);
        aPlus.updateProjects(478);
        aPlus.updateMidterm1(160);
        aPlus.updateMidterm2(218);
        aPlus.updateFinal(390);
        assertEquals("A+", aPlus.calculateGrade());

        //test sample A scores
        Main.FullGradeCalculator a = m.new FullGradeCalculator();
        a.updateHW(256);
        a.updateVitamins(48);
        a.updateProjects(480);
        a.updateMidterm1(160);
        a.updateMidterm2(140);
        a.updateFinal(390);
        assertEquals("A", a.calculateGrade());

        //test sample A- scores
        Main.FullGradeCalculator aMinus = m.new FullGradeCalculator();
        aMinus.updateHW(256);
        aMinus.updateVitamins(48);
        aMinus.updateProjects(480);
        aMinus.updateMidterm1(160);
        aMinus.updateMidterm2(140);
        aMinus.updateFinal(389);
        assertEquals("A-", aMinus.calculateGrade());

        //test extra credit bump
        aMinus.updateExtraCredit(1);
        assertEquals("A", aMinus.calculateGrade());
    }

    @Test
    public void testBRange() {
        Main m = new Main();

        //test sample B+ scores
        Main.FullGradeCalculator bPlus = m.new FullGradeCalculator();
        bPlus.updateHW(256);
        bPlus.updateVitamins(48);
        bPlus.updateProjects(480);
        bPlus.updateMidterm1(160);
        bPlus.updateMidterm2(140);
        bPlus.updateFinal(206);
        assertEquals("B+", bPlus.calculateGrade());

        //test sample B scores
        Main.FullGradeCalculator b = m.new FullGradeCalculator();
        b.updateHW(256);
        b.updateVitamins(48);
        b.updateProjects(480);
        b.updateMidterm1(160);
        b.updateMidterm2(140);
        b.updateFinal(200);
        assertEquals("B", b.calculateGrade());

        //test sample B- scores
        Main.FullGradeCalculator bMinus = m.new FullGradeCalculator();
        bMinus.updateHW(256);
        bMinus.updateVitamins(48);
        bMinus.updateProjects(480);
        bMinus.updateMidterm1(100);
        bMinus.updateMidterm2(100);
        bMinus.updateFinal(200);
        assertEquals("B-", bMinus.calculateGrade());
    }

    @Test
    public void testCRange() {
        Main m = new Main();

        //test sample C+ scores
        Main.FullGradeCalculator cPlus = m.new FullGradeCalculator();
        cPlus.updateHW(256);
        cPlus.updateVitamins(48);
        cPlus.updateProjects(480);
        cPlus.updateMidterm1(160);
        cPlus.updateMidterm2(140);
        cPlus.updateFinal(2);
        assertEquals("C+", cPlus.calculateGrade());

        //test sample C scores
        Main.FullGradeCalculator c = m.new FullGradeCalculator();
        c.updateHW(256);
        c.updateVitamins(48);
        c.updateProjects(480);
        c.updateMidterm1(100);
        c.updateMidterm2(100);
        c.updateFinal(2);
        assertEquals("C", c.calculateGrade());

        //test sample C- scores
        Main.FullGradeCalculator cMinus = m.new FullGradeCalculator();
        cMinus.updateHW(256);
        cMinus.updateVitamins(48);
        cMinus.updateProjects(480);
        cMinus.updateMidterm1(1);
        cMinus.updateMidterm2(0);
        cMinus.updateFinal(1);
        assertEquals("C-", cMinus.calculateGrade());

        //test that huge extra credit doesn't bump too high
        cMinus.updateExtraCredit(10000);
        assertNotEquals("A+", cMinus.calculateGrade());
        assertEquals("C-", cMinus.calculateGrade());
    }

    @Test
    public void testDRange() {
        Main m = new Main();

        //test sample D+ scores
        Main.FullGradeCalculator dPlus = m.new FullGradeCalculator();
        dPlus.updateHW(256);
        dPlus.updateVitamins(48);
        dPlus.updateProjects(400);
        dPlus.updateMidterm1(1);
        dPlus.updateMidterm2(1);
        dPlus.updateFinal(2);
        assertEquals("D+", dPlus.calculateGrade());

        //test sample D scores
        Main.FullGradeCalculator d = m.new FullGradeCalculator();
        d.updateHW(256);
        d.updateVitamins(48);
        d.updateProjects(300);
        d.updateMidterm1(1);
        d.updateMidterm2(1);
        d.updateFinal(2);
        assertEquals("D", d.calculateGrade());

        //test sample D- scores
        Main.FullGradeCalculator dMinus = m.new FullGradeCalculator();
        dMinus.updateHW(256);
        dMinus.updateVitamins(48);
        dMinus.updateProjects(200);
        dMinus.updateMidterm1(1);
        dMinus.updateMidterm2(0);
        dMinus.updateFinal(1);
        assertEquals("D-", dMinus.calculateGrade());
    }

    @Test
    public void testF() {
        Main m = new Main();

        Main.FullGradeCalculator f = m.new FullGradeCalculator();

        f.updateHW(256);
        f.updateMidterm1(50);
        f.updateMidterm2(10);

        assertEquals("F", f.calculateGrade());
    }

    @Test
    public void testNoArgs() {
        Main m = new Main();

        Main.FullGradeCalculator fullGradeCalculator = m.new FullGradeCalculator();

        assertEquals("F", fullGradeCalculator.calculateGrade());
    }*/
}
