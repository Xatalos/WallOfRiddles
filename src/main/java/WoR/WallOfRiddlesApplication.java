package WoR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import WoR.profiles.DevProfile;
import WoR.profiles.ProdProfile;

@SpringBootApplication
@Import({DevProfile.class, ProdProfile.class})
public class WallOfRiddlesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WallOfRiddlesApplication.class, args);
    }
}
