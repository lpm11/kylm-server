import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.msgpack.rpc.Server;
import org.msgpack.rpc.loop.EventLoop;

public class AppBase {
     /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        OptionParser optparse = new OptionParser();
        optparse.accepts("port","Listen port.").withRequiredArg();
        optparse.accepts("lm","Language model file name.").withRequiredArg();
        OptionSet opt = optparse.parse(args);

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
