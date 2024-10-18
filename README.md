# Laboratuvar Raporlama Uygulaması

## Tanıtım

Geliştirilen API laborantların raporlar hakkında işlem yapabilmesine olanak vermek için geliştirimiştir.
Bu amaçla Spring Boot, JPA ve Maven teknolojilerinden yararlanılmıştır.

Program iki adet sınıf içermektedir: Rapor ve Laborant. Bu sınıflar bir laborant birden çok rapor tanımlayabilecek şekilde 1-N ilişkisiyle bağlamıştır.

## İş Kuralları ve Kısıtlar

Rapor sınıfı; dosya numarası, hasta ad ve soyadı, hasta kimlik numarası, koyulan tanı başlığı, tanı detayları, raporun verildiği tarih ve fotoğraf field'larını içermelidir. 

Laborant sınıfı: ad, soyad, hastane kimlik numarası, rol ve şifre field'larına sahip olmalıdır.

Laborantlar ADMIN ve USER rollerinden birine sahip olmalıdır.

USER rolüne sahip laborantlar; rapor yaratma, güncelleme, listeleme, hasta ad/soyadına göre listeleme, hasta kimlik numarasına göre listeleme, laborant ad/soyad bilgilerine göre listeleme, tarihe göre listeleme yetkilerine sahiptir. 

ADMIN rolüne sahip laborantlar, USER rolüne sahip kullanıcıların yetkilerine ek olarak herhangi bir raporu silebilme yetkisine sahip olmalıdır.

Bir raporu sadece o raporu veren laborant güncelleyebilmelidir.

Laborantlar sisteme; isim, soyisim hastane kimlik numarası, şifre ve rolleri ile kayıt olabilmelidir.

## Çeşitli Teknik Seçimler

Uygulama geliştirilirken, boilerplate kodu azaltmak için Lombok anotasyon kütüphanesi kullanılmıştır.

Authorization için uygulamayı basit tutmak amacıyla Basic Auth kullanılmıştır.

Uygulama geliştirilme aşamasında olduğundan veritabanı olarak H2 veritabanı kullanılmıştır. Ayrıca DataLoader sınıfında uygulama başlatılırken veritabanına iki farklı kullanıcı yüklenmektedir.

Seperation of concerns prensipine bağlı olarak iş mantığı controller ve service katmanlarına ayrılmıştır. Oluşturulan rapor servisi dependency injection ile rapor controller'ında kullanılmaktadır. Rapor servisi ise aynı şekilde resim ve rapor mapper servislerini kullanmaktadır.

Kullanıcıya hassas kullanıcı verilerinin açık edilmemesi amacıyla RaporResponse DTO'su kullanıma sokulmuştur.

## Nasıl Kullanılır?

API'ı test etmek için Postman gibi bir API test programını kullanabilirsiniz. Endpointler aşağıda listelenmiştir.

### Kayıt Olma

**/kayitOl** : Sisteme kayıt olmak için bu endpointi kullanın. Örneğin:

**POST /kayitOl**
```json
{
  "kimlikNo": "1235684521",
  "password": "password",
  "isim": "Ahmet",
  "soyisim": "Yılmaz",
  "rol": "ADMIN"
}
```
### Yetki
Endpointlere erişim sağlayabilmek için kullanıcının sisteme giriş yapması gerekir. 
Bunun için Postman uygulamasında Authorization sekmesinde Basic Auth seçeneğini seçin ve kullanıcı bilgilerinizi girin.

### Kalan İşlemler

#### Rapor Tanımlama

**/rapor/tanimla** : Bu endpointi kullanarak rapor tanımlayabilsiniz. Örneğin:

**POST /rapor/tanimla**
```json
{
  "dosyaNumarasi": "D-456789",
  "hastaAd": "Berkay",
  "hastaSoyad": "Aydin",
  "tc": "34567890123",
  "tani": "Bronşit",
  "taniDetay": "Solunum yolları iltihabı, öksürük",
  "verildigiTarih": "2024-10-11T10:00:00.000Z"
} 
```

#### Hasta Ad Soyada Göre Listeleme
**/rapor/listele/hastaAdSoyad/{hastaAdi}/{hastaSoyad}** : Bu endpoint ile hasta adı ve soyadı için bütün raporları listeleyebilirsiniz. Örnek : 

>**GET rapor/listele/hastaAdSoyad/Faruk/Limon**

#### Hasta TC Numarasına Göre Listeleme
**/rapor/listele/hastaTc/{hastaTc}** : Bu endpoint ile hasta TC numarası için bütün raporları listeleyebilirsiniz. Örnek : 
 
>**GET /rapor/listele/hastaTc/25452624789**

#### Laborant Ad Soyada Göre Listeleme
**/rapor/listele/laborantAdSoyad/{laborantAd}/{laborantSoyad}** : Bu endpoint ile laborant ad ve soyadı için bütün raporları listeleyebilirsiniz. Örnek:
>**GET /rapor/listele/laborantAdSoyad/Mehmet/Kaya** 

#### Rapor Güncelleme
**/rapor/guncelle/{raporId}** Bu endpoint ile bir raporu güncelleyebilirsiniz. Örnek:
**PUT rapor/guncelle/1** 

```json
{
  "dosyaNumarasi": "D-4567123",
  "hastaAd": "Berkay",
  "hastaSoyad": "Altınay",
  "tc": "34567890123",
  "tani": "Bronşit",
  "taniDetay": "Solunum yolları iltihabı, öksürük",
  "verildigiTarih": "2024-10-12T10:00:00.000Z"
} 
```

#### Tüm Raporları Listeleme
**/rapor/listele** Bu endpoint ile bütün raporları listeleyebilirsiniz. Örnek: 
>**GET rapor/listele**

#### Rapor Silme
**/rapor/sil/{raporId}** Bu endpoint ile belli bir raporu silebilirsiniz. Örnek:
>**DELETE rapor/sil/1**

#### Resim Sorgulama
**rapor/resim/{raporId}** Bu endpoint ile belli bir rapor için oluşturulmuş görseli sorgulayabilirsiniz. Örnek:
>**GET rapor/resim/1**

#### Tarihe Göre Listeleme
>**rapor/listeleEnYeni rapor/listele/EnEski** Bu endpointler ile raporları tarihlerine göre listeyebilirsiniz.

#### Rapor Sorgulama
**rapor/sorgula/{id}** Bu endpoint ile rapor id'sine göre sorgu yapabilirsiniz. Örnek:
>**GET rapor/sorgula/1** 

## Kurulum
Bu repository'yi klonlayın : 
```bash 
git clone https://github.com/berkay6nay/Laboratuvar.git
```

Proje directory'sine erişin :
```bash 
cd Laboratuvar
```

JAR' ı çalıştırın :
```bash 
java -jar release/laboratuvar-0.0.1-SNAPSHOT.jar
```


