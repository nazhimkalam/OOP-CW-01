import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PremierLeagueTester
{
    @Test
    public void testAddMatch()
    {
        LeagueManager obj = PremierLeagueManager.getInstance();
        obj.createClub("barca","advavd","asdvasdv",null,"normal");
        obj.createClub("juventus","advavd","asdvasdv",null,"normal");
        obj.createClub("onco","advavd","asdvasdv",null,"normal");

        obj.addPlayedMatch(
                "2020-21","barca","juventus",1,2,
                null,"home"
        );

        for (int i = 0; i < 37; i++) {
            String result = obj.addPlayedMatch(
                    "2020-21","juventus","onco",1,2,
                    null,"home"
            );
            assertEquals("\n Match Successfully added! \n",result);
        }
        String result = obj.addPlayedMatch(
                "2020-21","onco","juventus",1,2,
                null,"home"
        );
        assertEquals("\n Sorry, 'juventus' has reached the maximum number of matches played!",result);
    }

}
