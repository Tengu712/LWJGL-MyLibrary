package blog;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	// 別スレッド用のサブクラス
	private class Play implements Runnable {

		AudioInputStream ais;
		boolean isPlaying;

		public Play(String path) {
			isPlaying = true;
			File file = new File(path);
			try {
				ais = getAudioInputStream(file);
			} catch (UnsupportedAudioFileException | IOException e) {
				throw new IllegalStateException(e);
			}
		}

		// 別スレッドで呼ばれ続ける
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isPlaying) {
				// 自作メソッドでAudioFormatを作る
				final AudioFormat outFormat = getOutFormat(ais.getFormat());
				// 音楽データの色々な情報を作る。らしい
				final Info info = new Info(SourceDataLine.class, outFormat);

				// 実際に音を流す
				try (final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
					if (line != null) {
						line.open(outFormat);
						line.start();
						stream(getAudioInputStream(outFormat, ais), line);
						line.drain();
						line.stop();
					}
				} catch (LineUnavailableException | IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}

		// AudioFormat作成
		private AudioFormat getOutFormat(AudioFormat inFormat) {
			final int ch = inFormat.getChannels();
			final float rate = inFormat.getSampleRate();
			return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
		}

		// データラインに音楽データを書き込む。んだと思います
		private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
			// 空のbufferの作成
			final byte[] buffer = new byte[65536];
			// ストリームからbufferにデータを読み取れなくなるまで読み取り
			// データラインに書き込んでいく
			for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
				line.write(buffer, 0, n);
				if (!isPlaying)
					break;
			}
		}

	}

	private Play p;
	private Thread th;

	public Sound() {
	}

	public void play(String path) {
		p = new Play(path);
		th = new Thread(p);
		th.start();
	}

	public void stop() {
		p.isPlaying = false;
	}
}
