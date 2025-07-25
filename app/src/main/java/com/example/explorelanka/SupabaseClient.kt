package com.example.explorelanka

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.storage

object SupabaseInstance {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://ocxlytkzvfkirtvcimat.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9jeGx5dGt6dmZraXJ0dmNpbWF0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTMzODg3MzYsImV4cCI6MjA2ODk2NDczNn0.JRTs5NRCzWev5TUNM4etT0vZOcSdaYlmtWBtKrG8p-A"
    ) {
        install(io.github.jan.supabase.storage.Storage)
    }
}

