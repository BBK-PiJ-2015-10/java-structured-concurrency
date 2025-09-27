import com.ap.dtos.*;
import com.ap.repos.*;
import com.ap.service.FindGitHubUserImpl;
import com.ap.service.FindGitHubUserUseCase;
import com.ap.service.FindGitHubUsersImpl;
import com.ap.service.FindGitHubUsersUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class UseCaseTest {

    @Test
    public void test1() {

        GitHubRepo gitHubRepo = new GitHubRepoRemoteImpl();
        UserRepo userRepo = new UserRepoImpl();
        FindGitHubUserUseCase useCase = new FindGitHubUserImpl(gitHubRepo, userRepo);
        FindGitHubUsersUseCase useCases = new FindGitHubUsersImpl(useCase);

        UserId userId1 = new UserId(1L);
        UserId userId2 = new UserId(2L);

        var result = useCases.findGitHubUsers(List.of(userId1, userId2));

        var repositories = List.of(
                new Repository(
                        "raise4s", Visibility.PUBLIC, URI.create("https://github.com/rcardin/raise4s")),
                new Repository(
                        "sus4s", Visibility.PUBLIC, URI.create("https://github.com/rcardin/sus4s")));
        var user1 = new User(new Username("alex"), userId1, new Email("alexis.gmail.com"));
        var user2 = new User(new Username("alex"), userId2, new Email("alexis.gmail.com"));
        var ghu1 = new GitHubUser(user1, repositories);
        var ghu2 = new GitHubUser(user2, repositories);
        var expectedResponse = List.of(ghu1, ghu2);

        Assert.assertEquals(expectedResponse.stream().collect(Collectors.toSet()), result.stream().collect(Collectors.toSet()));

    }

}
