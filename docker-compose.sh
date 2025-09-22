cd msvc-customer-person
mvn clean package -DskipTests
# Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
  echo "Error en la compilación. Abortando..."
  exit 1
fi
cd ../msvc-account-movement
mvn clean package -DskipTests
# Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
  echo "Error en la compilación. Abortando..."
  exit 1
fi
cd ..

docker-compose build
docker-compose up