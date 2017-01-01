package se.simplistics.template4fx;

import se.simplistics.template4fx.model.FileEntity;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileParser
    extends SimpleFileVisitor<Path>
{
    private final FileEntity root;

    private FileEntity current;

    public FileParser( FileEntity root )
    {
        this.root = root;
        current = root;
    }

    @Override
    public FileVisitResult preVisitDirectory( Path path, BasicFileAttributes basicFileAttributes )
        throws IOException
    {
        return super.preVisitDirectory( path, basicFileAttributes );
    }

    @Override
    public FileVisitResult visitFile( Path path, BasicFileAttributes basicFileAttributes )
        throws IOException
    {
        return super.visitFile( path, basicFileAttributes );
    }

    @Override
    public FileVisitResult postVisitDirectory( Path path, IOException e )
        throws IOException
    {
        return super.postVisitDirectory( path, e );
    }

    @Override
    public FileVisitResult visitFileFailed( Path path, IOException e )
        throws IOException
    {
        return super.visitFileFailed( path, e );
    }
}
