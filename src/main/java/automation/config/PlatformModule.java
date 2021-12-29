package automation.config;



import com.google.inject.Module;

public  enum PlatformModule {


    WEB {
        @Override
        public Module getModule() {
            return new WebModule();
        }
    };


    public abstract Module getModule();
}
