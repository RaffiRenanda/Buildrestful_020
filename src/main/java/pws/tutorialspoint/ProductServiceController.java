/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.tutorialspoint;

import java.util.HashMap;
import java.util.Map;
import model2.Product;
import org.springframework.http.HttpStatus;
import static org.springframework.http.RequestEntity.method;
import static org.springframework.http.RequestEntity.method;
import static org.springframework.http.RequestEntity.method;
import static org.springframework.http.RequestEntity.method;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TUF GAMING
 */
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        honey.setPrice(25000.00);
        honey.setDiscount(10);
        honey.setTotal(honey.getPrice(), honey.getDiscount());
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        almond.setPrice(30000.00);
        almond.setDiscount(10);
        almond.setTotal(almond.getPrice(), almond.getDiscount());
        productRepo.put(almond.getId(), almond);
    }
    
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        //membuat statment if 
        if(!productRepo.containsKey(id)) //jika id belum ada maka tidak bisa di hapus
        {
            return new ResponseEntity<>("Product missing", HttpStatus.NOT_FOUND);
        }
        else //membuat statment else
        {
            productRepo.remove(id);
        return new ResponseEntity<> ("Product is delete success", HttpStatus.OK);
        }
    }
        @RequestMapping (value = "/products/{id}", method = RequestMethod.PUT)
        public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
            //membuat statment if 
            if (!productRepo.containsKey(id)) //jika id belum ada maka tidak bisa di update
            {
             return new ResponseEntity<>("Product missing", HttpStatus.NOT_FOUND); 
            }
            else //membuat statment else
            {
                
            productRepo.remove(id);
            product.setId(id);
            product.setTotal(product.getPrice(), product.getDiscount());
            productRepo.put(id, product);
            return new ResponseEntity<>("Product update success", HttpStatus.OK);
            } 
            
        }
        @RequestMapping(value = "/products", method = RequestMethod.POST)
        public ResponseEntity<Object> createProduct(@RequestBody Product product){
            //membuat statment if 
            if(productRepo.containsKey(product.getId())) //jika id sudah ada maka akan gagal membuat data atau not found
            {
                return new ResponseEntity<>("Product missing", HttpStatus.NOT_FOUND);
                
            }
            else //membuat statment else
            {
            
            product.setTotal(product.getPrice(), product.getDiscount());
            productRepo.put(product.getId(), product);//jika id belum ada maka akan berhasil membuat data
            return new ResponseEntity<>("Product created succes", HttpStatus.OK); 
            }
        
        }
        @RequestMapping(value = "/products")
        public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
     }

    

}

/*
    @RequestMapping(value = "/product")
    public ResponseEntity<Object> getProduct(@RequestParam (value = "name", required = false, defaultValue = "honey") String name){}
            
    public ResponseEntity<Object> createProduct(@ReqestBody Product product){}
    
    public ResponseEntity<Object> updateProduct(@PathVariable("id" ) String id){}*/

