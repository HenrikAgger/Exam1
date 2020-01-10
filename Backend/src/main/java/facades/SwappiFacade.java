/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Henrik
 */
public class SwappiFacade {
    public String getSwappiData(int id, String apiurl) throws MalformedURLException, IOException{
        URL url = new URL(apiurl + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
          jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
      }

    public List<String> getAll() throws InterruptedException, ExecutionException{
        ExecutorService executor = Executors.newCachedThreadPool();
        
        List<Future<String>> futures = new ArrayList();
        
        for (int i = 1; i <= 10; i++) {
            final int count = i;
            futures.add(executor.submit(new Callable<String>() {
                
                @Override
                public String call() throws Exception {
                    return getSwappiData(count, "https://swapi.co/api/people/");
                }
            }));
            
        }
        List<String> result = new ArrayList();
        for (Future<String> future : futures) {
            result.add(future.get());
        }
        return result;
    }
    
}
