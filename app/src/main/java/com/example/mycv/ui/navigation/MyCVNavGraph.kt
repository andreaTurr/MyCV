import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycv.ui.home.CareerScreen
import com.example.mycv.ui.home.ContactScreen
import com.example.mycv.ui.home.EducationScreen
import com.example.mycv.ui.home.HomeScreen
import com.example.mycv.ui.home.IntroductionScreen
import com.example.mycv.ui.home.SkillsScreen

enum class ROUTES(val route:String) {
    HOME("HOME"),
    INTRODUCTION("INTRODUCTION"),
    CAREER("CAREER"),
    EDUCATION("EDUCATION"),
    SKILLS("SKILLS"),
    CONTACTS("CONTACTS")
}


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun MyCVNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController, startDestination = ROUTES.HOME.route, modifier = modifier
    ) {
        composable(route = ROUTES.HOME.route) {
            HomeScreen(navController)
        }
        composable(route = ROUTES.INTRODUCTION.route) {
            IntroductionScreen(navController = navController)
        }
        composable(route = ROUTES.CAREER.route) {
            CareerScreen(navController = navController)
        }
        composable(route = ROUTES.EDUCATION.route) {
            EducationScreen(navController = navController)
        }
        composable(route = ROUTES.SKILLS.route) {
            SkillsScreen(navController = navController)
        }
        composable(route = ROUTES.CONTACTS.route) {
            ContactScreen(navController = navController)
        }
    }
}
