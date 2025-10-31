export class EdgeViewer {
    private canvas: HTMLCanvasElement;
    private ctx: CanvasRenderingContext2D;
    private fpsElement: HTMLElement;
    private lastFrameTime: number = 0;
    private frameCount: number = 0;

    constructor() {
        this.canvas = document.getElementById('viewer') as HTMLCanvasElement;
        this.ctx = this.canvas.getContext('2d')!;
        this.fpsElement = document.getElementById('fps')!;
        this.initializeViewer();
    }

    private initializeViewer() {
        // Set initial canvas size
        this.canvas.width = 640;
        this.canvas.height = 480;
    }

    public displayFrame(imageData: ImageData | string) {
        if (typeof imageData === 'string') {
            // Handle base64 image
            const img = new Image();
            img.onload = () => {
                this.ctx.drawImage(img, 0, 0, this.canvas.width, this.canvas.height);
                this.updateFPS();
            };
            img.src = imageData;
        } else {
            // Handle ImageData
            this.ctx.putImageData(imageData, 0, 0);
            this.updateFPS();
        }
    }

    private updateFPS() {
        const now = performance.now();
        this.frameCount++;

        if (now - this.lastFrameTime >= 1000) {
            const fps = Math.round((this.frameCount * 1000) / (now - this.lastFrameTime));
            this.fpsElement.textContent = `FPS: ${fps}`;
            this.frameCount = 0;
            this.lastFrameTime = now;
        }
    }
}