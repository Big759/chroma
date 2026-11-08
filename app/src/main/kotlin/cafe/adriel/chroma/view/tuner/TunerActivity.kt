package cafe.adriel.chroma.view.tuner

import android.os.Bundle
import androidx.activity.compose.setContent
import cafe.adriel.chroma.ktx.keepScreenOn
import org.koin.androidx.scope.ScopeActivity

class TunerActivity : ScopeActivity() {

    private val screen by inject<TunerScreen>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { screen.Content() }
        keepScreenOn()
    }
}
