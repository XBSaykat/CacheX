package com.rommansabbir.cachexdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rommansabbir.cachex.CacheX
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val key: String = "authKey"
    private val keySingle = "authKeySingle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cacheX = CacheX(this)

        val model = UserAuth("romman", "testpass")
        val model1 = UserAuth("prottay", "testpass")

        val dataList = mutableListOf(model, model1)

        btnEncryptSingle.setOnClickListener {
            cacheX.doCache(
                model,
                keySingle,
                {
                    showMessage(model.username)
                },
                {
                    showMessage(it.message.toString())
                })
        }

        btnDecryptSingle.setOnClickListener {
            cacheX.getCache(
                UserAuth::class.java,
                keySingle,
                {
                    showMessage(it.username)
                },
                {
                    showMessage(it.message.toString())
                })


        }

        btnEncrypt.setOnClickListener {
            cacheX.doCacheList(
                dataList,
                key,
                {
                    for (item in dataList) {
                        showMessage(item.username)
                    }
                },
                {
                    showMessage(it.message.toString())
                })
        }

        btnDecrypt.setOnClickListener {
            cacheX.getCacheList<UserAuth>(
                key,
                {
                    showMessage(it.size.toString())
                },
                {
                    showMessage(it.message.toString())
                }
            )
        }

    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}