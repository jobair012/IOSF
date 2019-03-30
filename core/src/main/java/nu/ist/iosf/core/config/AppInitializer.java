package nu.ist.iosf.core.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { DbConfig.class };
//      return new Class[] { DevDatabaseConfig.class };
   }

//   @Override
//   protected Class<?>[] getRootConfigClasses() {
//      return new Class[0];
//   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebConfig.class };
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }

//   @Override
//   protected WebApplicationContext createRootApplicationContext() {
//      WebApplicationContext context = (WebApplicationContext) super.createRootApplicationContext();
//      ((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles("profiles");
//      return context;
//   }
}
