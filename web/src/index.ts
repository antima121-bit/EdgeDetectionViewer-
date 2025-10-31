import { EdgeViewer } from './edge-viewer';

const viewer = new EdgeViewer();

document.getElementById('loadSample')?.addEventListener('click', () => {
    const canvas = document.getElementById('viewer') as HTMLCanvasElement;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    const width = 640;
    const height = 480;
    const imageData = ctx.createImageData(width, height);
    const data = imageData.data;

    // Create a checkerboard pattern
    for (let y = 0; y < height; y++) {
        for (let x = 0; x < width; x++) {
            const i = (y * width + x) * 4;
            const color = ((x & 16) ^ (y & 16)) ? 255 : 0;
            data[i] = color;     // R
            data[i + 1] = color; // G
            data[i + 2] = color; // B
            data[i + 3] = 255;   // A
        }
    }
    
    viewer.displayFrame(imageData);
});
