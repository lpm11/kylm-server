import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.msgpack.rpc.Server;
import org.msgpack.rpc.loop.EventLoop;
import org.apache.log4j.xml.DOMConfigurator;
import java.io.File;

public class AppBase {
     /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        OptionParser optparse = new OptionParser();
        optparse.accepts("port","Listen port.").withRequiredArg();
        optparse.accepts("lm","Language model file name.").withRequiredArg();
        optparse.accepts("log4j","log4j configuration file.").withRequiredArg();
        OptionSet opt = optparse.parse(args);

        File file = new File("log4j.xml");
        if (opt.has("log4j") || file.exists()) {
        	DOMConfigurator.configure(opt.has("log4j") ? (String)opt.valueOf("log4j"):"log4j.xml");
        }

        KylmServer ks = new KylmServer((String)opt.valueOf("lm"));
    	EventLoop loop = EventLoop.defaultEventLoop();

        Server svr = new Server(loop);
        svr.serve(ks);

        try {
        	svr.listen(Integer.valueOf((String)opt.valueOf("port")));
        	loop.join();
        } finally {
        	svr.getEventLoop().shutdown();
        }
    }
}
