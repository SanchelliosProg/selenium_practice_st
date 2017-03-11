package utils;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

/**
 * Created by Александр on 10.03.2017.
 */
public class RandomizerTest {
    @Test
    public void generateTest(){
        System.out.println(Randomizer.getRandomEmailAddress("@mail.evil"));
    }

    @Test
    public void indexTest() {
        for (int i = 0; i < 10000; i++){
            int res = Randomizer.getUSAIndex();
            assertThat(res, is(lessThan(100000)));
        }
    }
}