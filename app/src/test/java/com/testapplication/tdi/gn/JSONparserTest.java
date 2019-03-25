package com.testapplication.tdi.gn;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JSONparserTest {
    private final String mHttpResponse = "{\"total_count\":1,\"incomplete_results\":false,\"items\":[{\"login\":\"TPDDK\",\"id\":36445076,\"node_id\":\"MDQ6VXNlcjM2NDQ1MDc2\",\"avatar_url\":\"https://avatars1.githubusercontent.com/u/36445076?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/TPDDK\",\"html_url\":\"https://github.com/TPDDK\",\"followers_url\":\"https://api.github.com/users/TPDDK/followers\",\"following_url\":\"https://api.github.com/users/TPDDK/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/TPDDK/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/TPDDK/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/TPDDK/subscriptions\",\"organizations_url\":\"https://api.github.com/users/TPDDK/orgs\",\"repos_url\":\"https://api.github.com/users/TPDDK/repos\",\"events_url\":\"https://api.github.com/users/TPDDK/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/TPDDK/received_events\",\"type\":\"User\",\"site_admin\":false,\"score\":20.36084}]}";
    @Test
    public void parse() {
        JSONparser parser = new JSONparser();
        ArrayList<GitHubItem> result;

        result = parser.parse(mHttpResponse);
        assertEquals(result.size(),1);
        assertEquals(result.get(0).user, "https://api.github.com/users/TPDDK");
    }
}