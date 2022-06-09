package com.app.repository;

import com.app.entity.UserEntity;
import org.neo4j.driver.*;
import org.springframework.stereotype.Repository;

import static org.neo4j.driver.Values.parameters;

@Repository
public class UserRepo implements AutoCloseable {

    private static final String URI = "bolt://localhost:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "1234";
    private static Driver driver = null;

    private static void Connection(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public boolean addUser(UserEntity s) {
        Connection(URI, USER, PASSWORD);
        try (Session session = driver.session()) {

            return session.writeTransaction(tx -> {

                Result result = tx.run("CREATE (a:sales{" + "product_id: $1_product_id" + ", price: $1_price" + ", stock: $1_stock" + ", currency: $1_currency" + ", brand: $1_brand" + ", serial_number: $1_serial_number" + ", product: $1_product" + ", category: $1_category" + ", created_on: $1_created_on" + ", updated_on: $1_updated_on" + ", model_number: $1_model_number" + ", product_name: $1_product_name" + ", category_type: $1_category_type" + ", weight: $1_weight" +

                        "}) return a ", parameters("1_product_id", s.getProduct_id(), "1_price", s.getPrice(), "1_stock", s.getStock(), "1_currency", s.getCurrency(), "1_brand", s.getBrand(), "1_serial_number", s.getSerial_number(), "1_product", s.getProduct(), "1_category", s.getCategory(), "1_created_on", s.getCreated_on(), "1_updated_on", s.getUpdated_on(), "1_model_number", s.getModel_number(), "1_product_name", s.getProduct_name(), "1_category_type", s.getCategory_type(), "1_weight", s.getWeight()

                ));

                return result.hasNext();
            });
        }
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub

    }


}
