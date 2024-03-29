package jadt.utils.audio;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Objects;

public class AudioMP3{

    private File file;
    private Player player;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;

    @Override
    public String toString() {
        return "AudioMP3{" +
                "file=" + file +
                ", player=" + player +
                ", fileInputStream=" + fileInputStream +
                ", bufferedInputStream=" + bufferedInputStream +
                '}';
    }

    public AudioMP3() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AudioMP3 audioMP3)) return false;
        return Objects.equals(file, audioMP3.file) && Objects.equals(player, audioMP3.player) && Objects.equals(fileInputStream, audioMP3.fileInputStream) && Objects.equals(bufferedInputStream, audioMP3.bufferedInputStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, player, fileInputStream, bufferedInputStream);
    }

    public AudioMP3(String MP3_File) throws FileNotFoundException, JavaLayerException {
        if(new File(MP3_File).exists()){
            this.file = new File(MP3_File);
        }
        assert file != null;
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if(index > 0 && file.exists()) {
            String extension = fileName.substring(index + 1);
            if(!extension.equals("mp3"))throw new IllegalArgumentException("File provided is not an mp3 File.");
        }
        else throw new FileNotFoundException("The MP3 file provided "+ this.file.getAbsolutePath() + " does not exist");
        if(this.file.isFile()) {
            this.fileInputStream = new FileInputStream(this.file.getAbsolutePath());
            this.bufferedInputStream = new BufferedInputStream(fileInputStream);
            this.player = new Player(bufferedInputStream);
        }
        if(this.file.isDirectory()) throw new IllegalStateException("File is a directory not a MP3 file");
    }
    public void play() throws FileNotFoundException {
        this.fileInputStream = new FileInputStream(file.getAbsolutePath());
        this.bufferedInputStream = new BufferedInputStream(fileInputStream);
        new Thread(() -> {
            try {
                player.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    
    }
    public void close() throws JavaLayerException, FileNotFoundException {
        player.close();
        this.fileInputStream = new FileInputStream(this.file.getAbsolutePath());
        this.bufferedInputStream = new BufferedInputStream(fileInputStream);
        this.player = new Player(bufferedInputStream);
    }
    public int getCurrentPlayingTime(){
        return player.getPosition();
    }
    public boolean isPlaying(){
        return !player.isComplete();
    }
    public boolean isFilePlayingComplete(){
        return player.isComplete();
    }
    public void setFile(String MP3_File) throws FileNotFoundException, JavaLayerException {
        if(new File(MP3_File).exists()){
            this.file = new File(MP3_File);
        }
        assert file != null;
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if(index > 0 && file.exists()) {
            String extension = fileName.substring(index + 1);
            if(!extension.equals("mp3"))throw new IllegalArgumentException("File provided is not an mp3 File.");
        }
        else throw new FileNotFoundException("The MP3 file provided "+ this.file.getAbsolutePath() + " does not exist");
        if(this.file.isFile()) {
            this.fileInputStream = new FileInputStream(this.file.getAbsolutePath());
            this.bufferedInputStream = new BufferedInputStream(fileInputStream);
            this.player = new Player(bufferedInputStream);
        }
        if(this.file.isDirectory()) throw new IllegalStateException("File is a directory not a MP3 file");

    }
    public File getFile(){
        return file;
    }
    public String getPath(){
        return this.file.getAbsolutePath();
    }
    public void deleteFile(){
        boolean temp = this.file.delete();
    }

    public Player getPlayer() {
        return player;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public BufferedInputStream getBufferedInputStream() {
        return bufferedInputStream;
    }
}
