import java.nio.ByteBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var glSurfaceView: GLSurfaceView

    private var imageAnalyzer: ImageAnalysis? = null
    private var preview: Preview? = null
    private var camera: Camera? = null
    private var isEdgeDetectionEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        glSurfaceView = findViewById(R.id.glSurfaceView)

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.toggleButton.setOnClickListener {
            isEdgeDetectionEnabled = !isEdgeDetectionEnabled
            binding.toggleButton.setImageResource(
                if (isEdgeDetectionEnabled) android.R.drawable.ic_menu_camera
                else android.R.drawable.ic_menu_view
            )
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
// ...existing code...
                        val buffer = image.planes[0].buffer
                        val data = ByteArray(buffer.remaining())
                        buffer.get(data)
                        
                        val processedData = if (isEdgeDetectionEnabled) {
                            processFrame(data, image.width, image.height)
                        } else {
                            data
                        }
                        
                        glSurfaceView.updateTexture(processedData, image.width, image.height)
                        
                        image.close()
                    })
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
// ...existing code...
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
// ...existing code...
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        
        init {
            System.loadLibrary("edge-detection")
        }
    }

    external fun processFrame(data: ByteArray, width: Int, height: Int): ByteArray
}