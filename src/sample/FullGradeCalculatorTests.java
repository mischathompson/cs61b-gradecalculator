package sample;
import org.junit.Test;
import static org.junit.Assert.*;

public class FullGradeCalculatorTests {
    @Test
    public void testARange() {
        //test sample A+ scores
        FullGradeCalculator aPlus = new FullGradeCalculator();
        aPlus.updateHW(256);
        aPlus.updateVitamins(48);
        aPlus.updateProjects(478);
        aPlus.updateMidterms(378);
        aPlus.updateFinal(390);
        assertEquals("A+", aPlus.calculateGrade());

        //test sample A scores
        FullGradeCalculator a = new FullGradeCalculator();
        a.updateHW(256);
        a.updateVitamins(48);
        a.updateProjects(480);
        a.updateMidterms(300);
        a.updateFinal(390);
        assertEquals("A", a.calculateGrade());

        //test sample A- scores
        FullGradeCalculator aMinus = new FullGradeCalculator();
        aMinus.updateHW(256);
        aMinus.updateVitamins(48);
        aMinus.updateProjects(480);
        aMinus.updateMidterms(300);
        aMinus.updateFinal(389);
        assertEquals("A-", aMinus.calculateGrade());

        //test extra credit bump
        aMinus.updateExtraCredit(1);
        assertEquals("A", aMinus.calculateGrade());
    }

    @Test
    public void testBRange() {
        //test sample B+ scores
        FullGradeCalculator bPlus = new FullGradeCalculator();
        bPlus.updateHW(256);
        bPlus.updateVitamins(48);
        bPlus.updateProjects(480);
        bPlus.updateMidterms(300);
        bPlus.updateFinal(206);
        assertEquals("B+", bPlus.calculateGrade());

        //test sample B scores
        FullGradeCalculator b = new FullGradeCalculator();
        b.updateHW(256);
        b.updateVitamins(48);
        b.updateProjects(480);
        b.updateMidterms(300);
        b.updateFinal(200);
        assertEquals("B", b.calculateGrade());

        //test sample B- scores
        FullGradeCalculator bMinus = new FullGradeCalculator();
        bMinus.updateHW(256);
        bMinus.updateVitamins(48);
        bMinus.updateProjects(480);
        bMinus.updateMidterms(200);
        bMinus.updateFinal(200);
        assertEquals("B-", bMinus.calculateGrade());
    }

    @Test
    public void testCRange() {
        //test sample C+ scores
        FullGradeCalculator cPlus = new FullGradeCalculator();
        cPlus.updateHW(256);
        cPlus.updateVitamins(48);
        cPlus.updateProjects(480);
        cPlus.updateMidterms(300);
        cPlus.updateFinal(2);
        assertEquals("C+", cPlus.calculateGrade());

        //test sample C scores
        FullGradeCalculator c = new FullGradeCalculator();
        c.updateHW(256);
        c.updateVitamins(48);
        c.updateProjects(480);
        c.updateMidterms(200);
        c.updateFinal(2);
        assertEquals("C", c.calculateGrade());

        //test sample C- scores
        FullGradeCalculator cMinus = new FullGradeCalculator();
        cMinus.updateHW(256);
        cMinus.updateVitamins(48);
        cMinus.updateProjects(480);
        cMinus.updateMidterms(1);
        cMinus.updateFinal(1);
        assertEquals("C-", cMinus.calculateGrade());

        //test that huge extra credit doesn't bump too high
        cMinus.updateExtraCredit(10000);
        assertNotEquals("A+", cMinus.calculateGrade());
        assertEquals("C-", cMinus.calculateGrade());
    }

    @Test
    public void testDRange() {
        //test sample D+ scores
        FullGradeCalculator dPlus = new FullGradeCalculator();
        dPlus.updateHW(256);
        dPlus.updateVitamins(48);
        dPlus.updateProjects(400);
        dPlus.updateMidterms(2);
        dPlus.updateFinal(2);
        assertEquals("D+", dPlus.calculateGrade());

        //test sample D scores
        FullGradeCalculator d = new FullGradeCalculator();
        d.updateHW(256);
        d.updateVitamins(48);
        d.updateProjects(300);
        d.updateMidterms(2);
        d.updateFinal(2);
        assertEquals("D", d.calculateGrade());

        //test sample D- scores
        FullGradeCalculator dMinus = new FullGradeCalculator();
        dMinus.updateHW(256);
        dMinus.updateVitamins(48);
        dMinus.updateProjects(200);
        dMinus.updateMidterms(1);
        dMinus.updateFinal(1);
        assertEquals("D-", dMinus.calculateGrade());
    }

    @Test
    public void testF() {
        FullGradeCalculator f = new FullGradeCalculator();

        f.updateHW(256);
        f.updateMidterms(50);

        assertEquals("F", f.calculateGrade());
    }

    @Test
    public void testNoArgs() {
        FullGradeCalculator fullGradeCalculator = new FullGradeCalculator();

        assertEquals("F", fullGradeCalculator.calculateGrade());
    }
}
