package ovh.motylek.meowintegrity.ui.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module


val coreModule = module {  }

@Module
@ComponentScan("ovh.motylek.meowintegrity.ui")
class ViewModelModule()
val modulesList = listOf(
    coreModule,
    ViewModelModule().module
)
