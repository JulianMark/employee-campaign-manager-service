package karate.campaign;

import com.intuit.karate.junit5.Karate;

public class CampaignRunner {

    @Karate.Test
    Karate testCampaignObtain() {
        return Karate.run("obtain").relativeTo(getClass());
    }

    @Karate.Test
    Karate testActiveCampaign() {
        return Karate.run("active").relativeTo(getClass());
    }

    @Karate.Test
    Karate testCampaignList() {
        return Karate.run("list").relativeTo(getClass());
    }
}
