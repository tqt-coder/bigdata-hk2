# Clear old files.
rm -r data_*.csv*
rm -r result_*

echo "----- Start Sales Analysis -----"


  # Downloading data file from the client system.
  wget http://$1:$2/download/data_1.csv

  # Loading data file into hive table
  hive -e "LOAD DATA LOCAL INPATH 'data_1.csv' OVERWRITE INTO TABLE sales";

  # Executing the query and creating the result file.
  hive -e 'SELECT * FROM sales GROUP BY product limit 10' > result_1
  hive -e 'SELECT product, count(product_id) FROM sales GROUP BY product' > result_2
  hive -e 'SELECT product, price FROM sales order by price desc limit 10' > result_3

  # Uploading the result file to the client system.
  curl -X POST http://$1:$2/upload -H 'content-type: multipart/form-data' -F file=@result_1
  curl -X POST http://$1:$2/upload2 -H 'content-type: multipart/form-data' -F file=@result_2
  curl -X POST http://$1:$2/upload3 -H 'content-type: multipart/form-data' -F file=@result_3

echo "----- End Sales Analysis -----"
