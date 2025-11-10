package cz.vican.scratchapp.scratch.system

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
// DÔLEŽITÝ IMPORT PRE OPRAVU:
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cz.vican.scratchapp.scratch.presentation.ScratchViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.roundToInt

@Composable
fun ScratchScreen() {
    val viewModel: ScratchViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    var scratching by remember { mutableStateOf(state.isScratched) }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}


//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Scratch Card") },
//                navigationIcon = {
//                    IconButton(onClick = {
////                        viewModel.cancelScratch()
////                        navController.popBackStack()
//                    }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = BluePrimary)
//            )
//        }
//    ) { padding ->
//        Column(
//            Modifier
//                .padding(padding)
//                .fillMaxSize()
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            ScratchCardView(card.state, card.code)
//            Spacer(Modifier.height(24.dp))
//
//            AnimatedVisibility(visible = !scratching) {
//                Button(
//                    onClick = {
//                        scratching = true
//                        viewModel.scratch()
//
//                    },
//                    shape = MaterialTheme.shapes.medium
//                ) {
//                    Text("Start Scratching")
//                }
//            }
//
//            if (scratching) {
//                CircularProgressIndicator(modifier = Modifier.size(40.dp))
//            }
//        }
//    }
//}
// --- 1. UPRAVENÝ ScratchCard Composable ---

// --- ScratchCard Composable (bez zmien, len pre kontext) ---
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ScratchCard(
    modifier: Modifier = Modifier,
    scratchedPoints: SnapshotStateList<Offset>,
    isUserInteractionEnabled: Boolean,
    scratchColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    scratchRadius: Float = 30f,
    content: @Composable () -> Unit
) {
    // Tu sa Path používa
    val scratchPath = remember { Path() }

    BoxWithConstraints(modifier = modifier) {
        content()

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (isUserInteractionEnabled) Modifier.pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { scratchedPoints.add(it) },
                            onDrag = { change, _ ->
                                change.consume()
                                scratchedPoints.add(change.position)
                            }
                        )
                    } else Modifier
                )
        ) {
            scratchPath.reset()
            scratchedPoints.forEach { point ->
                scratchPath.addOval(Rect(center = point, radius = scratchRadius))
            }

            drawRect(
                color = scratchColor,
                size = size
            )

            clipPath(scratchPath) {
                drawRect(
                    color = Color.Transparent,
                    size = size
                )
            }
        }
    }
}


// --- HLAVNÝ COMPOSE S TLAČIDLOM A LOGIKOU ANIMÁCIE (Opravené) ---

@Composable
fun Material3ScratchCardWithButtonAnimation() {
    val coroutineScope = rememberCoroutineScope()
    val scratchedPoints = remember { mutableStateListOf<Offset>() }
    var isAnimating by remember { mutableStateOf(false) }
    var isScratchedCompletely by remember { mutableStateOf(false) }

    // Používame LocalDensity na správny prepočet dp na pixely
    val density = LocalDensity.current.density

    // LaunchedEffect pre automatickú 2-sekundovú animáciu
    LaunchedEffect(isAnimating) {
        if (isAnimating) {
            scratchedPoints.clear()
            val duration = 2000L

            // Predpokladáme, že karta bude na obrazovke široká maximálne 800 pixelov a vysoká 200dp
            // Tieto hodnoty sú len simulácia! V skutočnej aplikácii by ste mali použiť rozmery Canvasu.
            val simWidth = 800f
            val simHeight = 200.dp.value * density

            val lineStep = 10f
            val pointStep = 5f
            val numLines = (simHeight / lineStep).roundToInt().coerceAtLeast(1)

            val numSteps = numLines // Celkový počet krokov (podľa počtu riadkov)

            for (i in 0 until numSteps) {
                val lineY = i * lineStep

                // Pridaj body pre celú šírku na danej Y-súradnici
                for (x in 0..simWidth.roundToInt() step pointStep.roundToInt()) {
                    scratchedPoints.add(Offset(x.toFloat(), lineY))
                }

                // Časové oneskorenie pre hladkú animáciu
                delay(duration / numSteps)
            }

            isAnimating = false
            isScratchedCompletely = true
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Škrabacia karta s animáciou", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.9f).height(200.dp),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            ScratchCard(
                modifier = Modifier.fillMaxSize(),
                scratchedPoints = scratchedPoints,
                isUserInteractionEnabled = !isAnimating && !isScratchedCompletely,
                scratchRadius = 30f
            ) {
                // --- SKRYTÝ OBSAH ---
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "GRATULUJEME!",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Vyhrali ste 1.000.000 €!",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                when {
                    !isAnimating && !isScratchedCompletely -> isAnimating = true
                    isScratchedCompletely -> {
                        scratchedPoints.clear()
                        isScratchedCompletely = false
                    }
                }
            },
            enabled = !isAnimating
        ) {
            Text(
                when {
                    isAnimating -> "Animujem..."
                    isScratchedCompletely -> "Resetovať Kartu"
                    else -> "Spustiť Animáciu (2s)"
                }
            )
        }

        if (!isAnimating && !isScratchedCompletely) {
            Text("Alebo ju zoškrabte prstom!", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

// Stav pre uchovanie finálnych rozmerov karty v pixeloch
data class CardSize(val width: Float, val height: Float) {
    companion object {
        val Zero = CardSize(0f, 0f)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScratchCardScreen() {
    val scratchedPoints = remember { mutableStateListOf<Offset>() }
    var isAnimating by remember { mutableStateOf(false) }
    var isScratchedCompletely by remember { mutableStateOf(false) }

    // Stav pre uchovanie rozmerov karty
    var cardSize by remember { mutableStateOf(CardSize.Zero) }

    // LaunchedEffect pre automatickú 2-sekundovú animáciu
    LaunchedEffect(isAnimating, cardSize) {
        if (isAnimating && cardSize != CardSize.Zero) {
            scratchedPoints.clear()
            val duration = 2000L

            val simWidth = cardSize.width
            val simHeight = cardSize.height

            // Parametre animácie
            val lineStep = 10f
            val pointStep = 5f
            val numLines = (simHeight / lineStep).roundToInt().coerceAtLeast(1)

            val numSteps = numLines
            val delayPerStep = duration / numSteps

            for (i in 0 until numSteps) {
                val lineY = i * lineStep

                // Generovanie bodov horizontálne
                for (x in 0..simWidth.roundToInt() step pointStep.roundToInt()) {
                    if (x.toFloat() <= simWidth && lineY <= simHeight) {
                        scratchedPoints.add(Offset(x.toFloat(), lineY))
                    }
                }

                delay(delayPerStep) // Časové oneskorenie
            }

            isAnimating = false
            isScratchedCompletely = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Scratch Card Demo") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Simulácia Animácie & Interakcia",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(16.dp))

            // Card Composable s onSizeChanged pre získanie presných rozmerov
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(200.dp)
                    // Získanie finálnych rozmerov v pixeloch a ich uloženie do stavu
                    .onSizeChanged { size ->
                        cardSize = CardSize(size.width.toFloat(), size.height.toFloat())
                    },
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                ScratchCard(
                    modifier = Modifier.fillMaxSize(),
                    scratchedPoints = scratchedPoints,
                    isUserInteractionEnabled = !isAnimating && !isScratchedCompletely,
                    scratchRadius = 30f
                ) {
                    // --- SKRYTÝ OBSAH (to, čo sa odhalí po zoškrabaní) ---
                    Column(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "GRATULUJEME!",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Vyhrali ste 1.000.000 €!",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Tlačidlo, ktoré spustí animáciu / reset
            Button(
                onClick = {
                    when {
                        cardSize != CardSize.Zero && !isAnimating && !isScratchedCompletely -> isAnimating = true
                        isScratchedCompletely -> {
                            scratchedPoints.clear()
                            isScratchedCompletely = false
                        }
                    }
                },
                // Tlačidlo je dostupné len, ak poznáme rozmery a nie je už animácia spustená
                enabled = !isAnimating && cardSize != CardSize.Zero
            ) {
                Text(
                    when {
                        isAnimating -> "Animujem..."
                        isScratchedCompletely -> "Resetovať Kartu"
                        cardSize == CardSize.Zero -> "Čakám na rozmery..."
                        else -> "Spustiť Animáciu (2s)"
                    }
                )
            }

            if (!isAnimating && !isScratchedCompletely) {
                Text(
                    "Alebo ju zoškrabte prstom!",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}