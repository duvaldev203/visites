
cd ressources/api/swagger/
java -jar swagger-codegen-cli-3.0.46.jar generate -i http://localhost:8080/api/v3/api-docs -l typescript-axios -o ../../../src/frontend/react-admin/src/generated
# java -jar swagger-codegen-cli-3.0.46.jar generate -i ../../../src/backend/spring-boot/target/swagger-ui/api-docs.yaml -l typescript-axios -o ../../../src/frontend/react-admin/src/generated