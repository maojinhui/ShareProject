package com.mao.shareproject.study;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MyHash<K,V> extends AbstractMap<K,V> implements Map<K,V> {

    static final int initsize = 1<<4;






    @NonNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Nullable
    @Override
    public V getOrDefault(@Nullable Object key, @Nullable V defaultValue) {
        return null;
    }

    @Override
    public void forEach(@NonNull BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void replaceAll(@NonNull BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Nullable
    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(@Nullable Object key, @Nullable Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, @Nullable V oldValue, V newValue) {
        return false;
    }

    @Nullable
    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Nullable
    @Override
    public V computeIfAbsent(K key, @NonNull Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Nullable
    @Override
    public V computeIfPresent(K key, @NonNull BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Nullable
    @Override
    public V compute(K key, @NonNull BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Nullable
    @Override
    public V merge(K key, @NonNull V value, @NonNull BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }
}
