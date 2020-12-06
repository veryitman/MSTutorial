## 签名证书

### 1、生成证书
keytool -genkey -alias testalias -keyalg RSA -keysize 2048 -validity 36500 -keystore tutorial.keystore

### 2、迁移（转换）到行业标准格式 PKCS12
keytool -importkeystore -srckeystore tutorial.keystore -destkeystore tutorial.keystore -deststoretype pkcs12

### 3、查看签名文件详情，需要密钥m....t
 keytool -list -v -keystore tutorial.keystore