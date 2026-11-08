package cafe.adriel.chroma.di

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import cafe.adriel.chroma.manager.MessagingManager
import cafe.adriel.chroma.manager.PermissionManager
import cafe.adriel.chroma.manager.SettingsManager
import cafe.adriel.chroma.manager.TunerManager
import cafe.adriel.chroma.view.tuner.TunerActivity
import cafe.adriel.chroma.view.tuner.TunerScreen
import cafe.adriel.chroma.view.tuner.TunerViewModel
import cafe.adriel.satchel.Satchel
import cafe.adriel.satchel.storer.file.FileSatchelStorer
import java.io.File
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    scope<TunerActivity> {
        viewModel {
            TunerViewModel(
                tunerManager = get(),
                settingsManager = get(),
                permissionManager = get(),
                messagingManager = get()
            )
        }

        scoped {
            TunerScreen(
                viewModel = get()
            )
        }

        scoped {
            TunerManager(
                settingsManager = get(),
                permissionManager = get(),
                lifecycleOwner = getSource<TunerActivity>()
            )
        }

        scoped {
            PermissionManager(
                activity = getSource<TunerActivity>()
            )
        }

        scoped {
            MessagingManager(
                context = getSource<TunerActivity>()
            )
        }
    }

    single {
        SettingsManager(
            storage = get(),
            scope = get()
        )
    }

    single {
        Satchel.with(
            storer = FileSatchelStorer(
                file = File(get<Application>().filesDir, "settings.storage")
            )
        )
    }

    single<CoroutineScope> {
        ProcessLifecycleOwner.get().lifecycleScope
    }
}
