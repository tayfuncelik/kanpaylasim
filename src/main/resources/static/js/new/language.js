var app = angular.module('local_language', [ 'pascalprecht.translate' ]);

app.config(function($translateProvider) {
	$translateProvider.translations('en', {
		AB_NEG : 'ABrh-',
		AB_POZ : 'ABrh+',
		SIFIR_POZ : '0rh+',
		SIFIR_NEG : '0rh-',
		A_NEG : 'Arh-',
		A_POZ : 'Arh+',
		B_NEG : 'Brh-',
		B_POZ : 'Brh+',
		DONOR : {
			DONOR_UPDATE : 'Donör Bilgisi Güncelleme',
			NAME : 'İsim',
			AGE : 'Yaş',
			WEIGHT : 'Kilo',
			BLOOD_PRESSURE : 'Kan Basıncı',
			LAST_DONATION_DATE : 'Son Kan Veris Tarihi',
			HOSPITAL_LOCATION : 'Hastane Lokasyonu',
			GET_LOCATION : 'Konum Bilgisi Al',
			SICKNESS : 'Hastalıklar',
			OPERATIONS : {
				SURGERY : 'Ameliyat Geçirdiniz mi',
				FEEL_GOOD : 'Kendinizi iyi ve sağlıklı hissediyor musunuz.?',
				VIRAL_HEPATIT : 'Viral hepatit geçirdiniz mi?',
				BLOOD_TRANSFER : 'Son 12 ay içinde kan transferi yapıldımı',
				AIDS : 'HIV(AIDS) tesiniz positif mi ?',
				SITMA : 'Son 3 yıl içinde sıtma(malarya) geçirdiniz mi?',
				BRAIN_SURGERY : 'Hiç beyin ameliyatı oldunuz mu ?',
				TAKEN_ALCOHOL : '12 saatten once alkok aldiniz mi ?*',
				ANEMI : 'Kansızlık durumunuz var mı ?',
				HARMFULL_MEDICINE : 'Aşağıdaki ilaçlardan kullandınız  mı?'
			},
			YES : 'EVET',
			NO : 'HAYIR',
			BACK : 'GERİ',
			CREATE : 'OLUŞTUR',
			UPDATE : 'GÜNCELLE',
			ADD_DONOR : 'Donör Ekle',
			REMOVE_DONOR : 'Donör Çıkar',
			GO_TO_MAP : 'Haritaya Git',
			SURGERY : 'Ameliyat Geçirmiş',
			SICKNESS : 'Hastalıklar Var mı',
			BLOOD_TYPE : 'Kan Grubu',
			RECORDS_FOUND : 'Kayıt Bulundu',
			DONOR_LIST : 'Donör Listesi',
			DONOR_LOCATION : 'Donörün bulunduğu konum'
		},

		EVENT : {
			NEEDER_LIST : "HASTA LİSTESİ",
			START_DATE : "Başlangıç Tarihi",
			END_DATE : "Bitiş Tarihi",
			ACTION : "Aksiyon",
			CREATE : "Kayıt Oluştur",
			DELETE : "Kayıt Sil",
			BLOOD_NEED : "Kan ihtiyaç",
			UPDATE:"Güncelle",
			BACK:"Geri",
			HOSPITAL_LOCATION:"Hastane Konumu",
			HASTA_ADI:"Hasta/Hastahane/Doktor ismi",
			HASTA_INFO:"Hasta Kayıt Güncelleme"
		},

		INDEX_PAGE : {

			MENU : "MENÜ",
			DONOR_LIST : "Donör Listesi",
			HASTA_LIST : "Hasta Listesi",
			MATCHED_NEEDER : "Eşleşen Hasta",
			FOUND_DONOR : "Bulunan Donör",
			PROFILE : "Profil",
			USER : "Kullanıcılar",
			EXIT : "ÇIKIŞ YAP",
			LOGON:"GİRİŞ /KAYIT"

		},
		MATCHED_DONOR : {
			START_DATE : 'Başlangıç Tarihi:',
			END_DATE : 'Bitiş Tarihi',
			BLOOD_TYPE : 'KAN GRUBU',
			DONATION_DATE : 'BAĞIŞ TARİHİ',
			NEEDER_NAME : "Hasta/Hastahane/Doktor ismi",
			ACTION : 'Aksiyon',
			MATCH_LIST : "Donör için eşleşen hasta listesi",
			MATCH_DONOR_LIST : "Hasta/Doktorun aradığı donör eşleşmeleri"
		},
		PROFILE : {
			PROFILE : "KULLANICI BİLGİLERİ",
			UPDATE : "Güncelle",
			USERNAME : "Kullanıcı Adı",
			NAME : "İsim",
			LAST_NAME : "Soy İsim",
			PASSWORD : "Şifre",
			HIDE_PASSWORD : "Şifreyi Gizle",
			SHOW_PASSWORD : "Şifreyi Göster",
			CONFIRM_PASSWORD : "Şifreyi Doğrula"
		},
		ALTINBAS_UNIV : "Altınbaş Üniversitesi Tez Projesidir ",
	
		
		MAIN_PAGE:{
			SAVE_LIVES_WITH_DONATION : "KAN BAĞIŞI YAP HAYAT KURTAR",
			BLOOD_DONATION_CONDITON:"KAN BAĞIŞINA UYGUNLUK İÇİN ARANAN KRİTERLER",
			FEEL_GOOD:"Kendinizi iyi ve sağlıklı hissediyor musunuz?",
			HEPATIT:"Viral hepatit geçirdiniz mi? ",
			TRANSFISION:"Son 12 ay içinde size; kan transfüzyonu yapıldı mı? ",
			CONTIGIOUS:"Son 12 ay içinde; bulaşıcı sarılığı olan biri ile yakın temasınız (örneğin cinsel ilişki) oldu mu? ",
			HIV:"HIV (AIDS) testiniz pozitif mi ? ",
			KIZILAY_ARAC:"KIZILAY ARAÇLARI",
			SAGLIK_KURULUS:"SAĞLIK KURULUŞLARI"	,
			ACIL_KAN:"ACİL KAN ARANIYOR",
			USE_APP:"Daha fazlası için siteye kayıt olun hayat kurtarın"
		},
		LOGIN:{
			LOGIN:"GİRİŞ YAP",
			USERNAME:"Kullanıcı Adı",
			PASSWORD:"Şifre",
			REGISTER:"KAYIT OL",
			NAME:"İsim",
			LAST_NAME:"Soy isim",
			CONFIRM_PASSWORD:"Şifreyi Tekrar Giriniz"
		},
		USER:{
			RECORD_FOUND:"Kayıt Bulundu",
			USERNAME:"Kullanıcı Adı",
			ACTION:"Aksiyon",
			USER_INFO:"Kullanıcı Oluştur",
			SUBMIT:"YARAT",
			BACK:"GERİ",
			NAME:"Ad",
			LAST_NAME:"Soy Ad"
				
		}
	 
			
	});
	$translateProvider.translations('de', {
		TITLE : 'Hallo',
		FOO : 'Dies ist ein Paragraph.',
		BUTTON_LANG_EN : 'englisch',
		BUTTON_LANG_DE : 'deutsch'
	});
	$translateProvider.preferredLanguage('en');
	// $translateProvider.useSanitizeValueStrategy('escaped');
	// $translateProvider.useSanitizeValueStrategy('sanitize');
});