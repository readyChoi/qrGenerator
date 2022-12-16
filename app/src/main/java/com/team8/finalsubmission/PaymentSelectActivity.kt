package com.team8.finalsubmission

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.team8.finalsubmission.databinding.ActivityPaymentSelectBinding


class PaymentSelectActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityPaymentSelectBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언

    private val binding get() = mBinding!!

    val iv: ImageView? = null

    lateinit var databaseMenu: DatabaseReference //메뉴 데이터베이스
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityPaymentSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val qrString = intent.getStringExtra("qrString")
        val qrString2 = qrString?.let { String(it.toByteArray(Charsets.UTF_8), Charsets.ISO_8859_1) }



        databaseMenu	=	Firebase.database.getReference("menu")


        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(qrString2, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            binding.qrcode.setImageBitmap(bitmap)
        } catch (e: Exception) {
        }
    }
}