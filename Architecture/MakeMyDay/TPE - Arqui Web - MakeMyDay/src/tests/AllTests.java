package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCargarUsuario.class, TestCargarTipoActividades.class, TestCargarActividades.class, TestCargarActividadesRealizadas.class, TestCargarHistoriales.class,
		TestConsultasDB.class })
public class AllTests {

}
