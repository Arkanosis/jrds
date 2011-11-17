package jrds.probe;

import jrds.PropertiesManager;
import jrds.starter.Starter;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpClientStarter extends Starter {
    private static final String USERAGENT = "JRDS HTTP agent";
    HttpClient client = null;
    int maxConnect = 0;
    int timeout = 0;
    
    /* (non-Javadoc)
     * @see jrds.starter.Starter#configure(jrds.PropertiesManager)
     */
    @Override
    public void configure(PropertiesManager pm) {
        super.configure(pm);
        maxConnect = pm.collectorThreads;
        timeout = pm.timeout * 1000;
    }

    @Override
    public boolean start() {
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
        cm.setMaxTotal(maxConnect * 2);
        cm.setDefaultMaxPerRoute(2);

        client = new DefaultHttpClient(cm);
        HttpParams params = client.getParams();
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
        HttpProtocolParams.setUserAgent(params, USERAGENT);
        return true;
    }

    @Override
    public void stop() {
        client.getConnectionManager().shutdown();
        client = null;
    }

    public HttpClient getHttpClient() {
        return client;
    }
}
